package io.keepcoding.discourse_android.UI.topics

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.TopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.LatestTopicResponse
import io.keepcoding.discourse_android.R
import kotlinx.android.synthetic.main.topics_fragment.*
import kotlinx.android.synthetic.main.view_topics_error.*
import retrofit2.Response
import java.lang.IllegalArgumentException

class TopicsFragment() : Fragment(), CallbackTopicClick {

    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var topicsInteractionListener: TopicsInteractionListener? = null
    private var mTopics: List<TopicItem>? = null
    private var topicsAdapter: TopicsAdapter? = null

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
        listTopics.layoutManager = LinearLayoutManager(activity)
        listTopics.isNestedScrollingEnabled = false
        listTopics.setHasFixedSize(false)
        buttonCreate.setOnClickListener {
            this.topicsInteractionListener?.onCreateTopic()
        }

        swipeRefreshLayout = view?.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout?.setProgressViewEndTarget(true, 0)
        swipeRefreshLayout?.setOnRefreshListener {
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
            fragmentLoadingContainer.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            fragmentLoadingContainer.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }
    }

    private fun getTopics(){
        enableLoading()
        mViewModel.getTopics(object: DiscourseService.CallbackResponse<LatestTopicResponse> {
            override fun onResponse (response: LatestTopicResponse) {
                enableLoading(false)
                viewError.visibility = View.INVISIBLE
                swipeRefreshLayout?.isRefreshing = false
                mTopics = mViewModel.parseTopics(response)
                topicsAdapter = TopicsAdapter(requireActivity().applicationContext, this@TopicsFragment, mTopics)
                listTopics.adapter = topicsAdapter
            }
            override fun onFailure(t: Throwable, res: Response<*>?) {
                enableLoading(false)
                viewError.visibility = View.VISIBLE
                swipeRefreshLayout?.isRefreshing = false
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

    override fun onItemClick(topic: TopicItem) {
        //go to detail
    }

}