package io.keepcoding.discourse_android.UI.topics.topic_detail

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.PostItem
import io.keepcoding.discourse_android.Data.Models.AppModels.SingleTopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SingleTopicResponse
import io.keepcoding.discourse_android.R
import kotlinx.android.synthetic.main.topic_detail_activity.*
import retrofit2.Response

class TopicDetailActivity: AppCompatActivity() {

    private val mViewModel: TopicDetailViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(TopicDetailViewModel::class.java)
    }

    private var topic: SingleTopicItem? = null
    private var topicId: String? = ""
    private var postsList: List<PostItem>? = null
    private var postAdapter: PostAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_detail_activity)
        init()
        back_button.setOnClickListener{
            finish()
        }

        reply_button.setOnClickListener(){
            //go to reply
        }

        intent?.let {
            topicId = intent.getStringExtra("TOPIC_ID")
            mViewModel.getSingleTopic(object: DiscourseService.CallbackResponse<SingleTopicResponse>{
                override fun onResponse(response: SingleTopicResponse) {
                    topic = mViewModel.parseSingleTopic(response)
                    postsList = topic?.posts
                    mViewModel.showTopic(
                            this@TopicDetailActivity,
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

                    postAdapter = PostAdapter(applicationContext, postsList)
                    listPost.adapter = postAdapter
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {

                }
            }, topicId = topicId!!)
        }

    }

    private fun init(){
        listPost.layoutManager = LinearLayoutManager(this)
        listPost.isNestedScrollingEnabled = false
        listPost.setHasFixedSize(false)
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(this, R.font.avenir_next_bold)
        back_button.typeface = myCustomFontBold
        toolbar_text.typeface = myCustomFontBold
        topic_detail_container.setBackgroundColor(Color.parseColor("#F8F8F8"))
    }



}