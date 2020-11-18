package io.keepcoding.discourse_android.UI.topics

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.TopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.LatestTopicResponse
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.topics.topic_detail.TopicDetailActivity
import kotlinx.android.synthetic.main.topics_fragment.*
import kotlinx.android.synthetic.main.view_topics_error.*
import retrofit2.Response
import java.lang.IllegalArgumentException

class TopicsFragment() : Fragment(), CallbackTopicClick {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: TopicsAdapter

    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var topicsInteractionListener: TopicsInteractionListener? = null
    private var mTopics: MutableList<TopicItem>? = null
    private var topicsAdapter: TopicsAdapter? = null
    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()
    var page: Int = 1
    var loading: Boolean = false


    private val mViewModel: TopicsFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(TopicsFragmentViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TopicsInteractionListener)
            topicsInteractionListener = context
        else
            throw IllegalArgumentException("Context doesn't implement ${TopicsInteractionListener::class.java.canonicalName}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.topics_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        linearLayoutManager = LinearLayoutManager(context)
        listTopics.layoutManager = linearLayoutManager
        listTopics.isNestedScrollingEnabled = false
        listTopics.setHasFixedSize(false)
        buttonCreate.setOnClickListener {
            this.topicsInteractionListener?.onCreateTopic()
        }

        swipeRefreshLayout = view?.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout?.setProgressViewEndTarget(true, 0)
        swipeRefreshLayout?.setOnRefreshListener {
            mTopics = null
            getTopics()
        }

        buttonRetry.setOnClickListener {
            getTopics()
        }
         listTopics.setBackgroundColor(Color.parseColor("#F8F8F8"))
    }

    override fun onResume() {
        super.onResume()
        getTopics()
    }

    private fun enableLoading(enabled: Boolean = true)  {
        if (enabled) {
            loading = true
            fragmentLoadingContainer.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            loading = false
            fragmentLoadingContainer.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }
    }

    private fun getTopics(page: Int = 0){
        enableLoading()
        mViewModel.getTopics(object: DiscourseService.CallbackResponse<LatestTopicResponse> {
            override fun onResponse(response: LatestTopicResponse) {
                enableLoading(false)
                viewError.visibility = View.INVISIBLE
                swipeRefreshLayout?.isRefreshing = false
                if (mTopics != null){
                    var newTopics = mViewModel.parseTopics(response)
                        if (newTopics != null) {
                            for (topic in newTopics) {
                                mTopics!!.add(topic)
                                topicsAdapter?.notifyItemInserted(mTopics!!.size-1)
                            }
                    }
                } else {
                    mTopics = mViewModel.parseTopics(response)
                    topicsAdapter = TopicsAdapter(requireActivity().applicationContext, this@TopicsFragment, mTopics)
                    setRecyclerViewScrollListener()
                    listTopics.adapter = topicsAdapter
                }
            }
            override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {
                enableLoading(false)
                viewError.visibility = View.VISIBLE
                swipeRefreshLayout?.isRefreshing = false
            }


        }, page = page)
    }

    private fun setRecyclerViewScrollListener() {
        listTopics.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1 && !loading) {
                    if (page < 15) {
                        page += 1
                        getTopics(page)
                    }
                }
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        topicsInteractionListener = null
    }

    interface TopicsInteractionListener {
        fun onCreateTopic()
    }

    override fun onItemClick(topicId: String) {
        context?.let { fragment ->
            val intent = Intent(fragment, TopicDetailActivity::class.java).apply {
                putExtra("TOPIC_ID", topicId)
            }
            startActivity(intent)
        }
    }

}