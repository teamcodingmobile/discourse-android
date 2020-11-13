package io.keepcoding.discourse_android.UI.topics.topic_detail

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.PostItem
import io.keepcoding.discourse_android.Data.Models.AppModels.SingleTopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SingleTopicResponse
import io.keepcoding.discourse_android.R
import kotlinx.android.synthetic.main.topic_detail_fragment.*
import retrofit2.Response
import java.lang.IllegalArgumentException

class TopicDetailFragment(var topicId: String = "") : Fragment() {

    var interactionListener: TopicDetailInteractionListener? = null

    private val mViewModel: TopicDetailViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(TopicDetailViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TopicDetailInteractionListener)
            interactionListener = context
        else
            throw IllegalArgumentException("Context doesn't implement ${TopicDetailInteractionListener::class.java.canonicalName}")
    }

    private var topic: SingleTopicItem? = null
    private var postsList: List<PostItem>? = null
    private var postAdapter: PostAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.topic_detail_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        back_button.setOnClickListener{
           interactionListener?.onBackButton()
        }
        mViewModel.getSingleTopic(object: DiscourseService.CallbackResponse<SingleTopicResponse>{
            override fun onResponse(response: SingleTopicResponse) {
                topic = mViewModel.parseSingleTopic(response)
                postsList = topic?.posts
                mViewModel.showTopic(
                        this@TopicDetailFragment,
                        topic,
                        posterImage,
                        posterUsername,
                        labelDate,
                        labelTitle,
                        labelViews,
                        labelReplies,
                        labelPosts,
                        linearLayout
                )

                postAdapter = PostAdapter(requireActivity().application, postsList)
                listPost.adapter = postAdapter
            }

            override fun onFailure(t: Throwable, res: Response<*>?) {

            }
        }, topicId = topicId!!)
    }


    private fun init() {
        listPost.layoutManager = LinearLayoutManager(requireActivity().application)
        listPost.isNestedScrollingEnabled = false
        listPost.setHasFixedSize(false)
        topic_detail_container.setBackgroundColor(Color.parseColor("#F8F8F8"))
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(requireActivity().application, R.font.avenir_next_bold)
        back_button.typeface = myCustomFontBold
        toolbar_text.typeface = myCustomFontBold
    }

    interface TopicDetailInteractionListener {
        fun onBackButton()
    }


}