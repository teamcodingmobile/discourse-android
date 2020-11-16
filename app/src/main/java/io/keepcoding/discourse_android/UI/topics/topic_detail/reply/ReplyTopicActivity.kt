package io.keepcoding.discourse_android.UI.topics.topic_detail.reply

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.PostModel
import io.keepcoding.discourse_android.Data.Models.AppModels.SingleTopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.PostResponse
import io.keepcoding.discourse_android.R
import kotlinx.android.synthetic.main.reply_topic_activity.*
import kotlinx.android.synthetic.main.reply_topic_activity.labelDate
import kotlinx.android.synthetic.main.reply_topic_activity.labelTitle
import kotlinx.android.synthetic.main.reply_topic_activity.posterImage
import kotlinx.android.synthetic.main.reply_topic_activity.posterUsername
import kotlinx.android.synthetic.main.reply_topic_activity.reply_button
import kotlinx.android.synthetic.main.reply_topic_activity.toolbar_text
import kotlinx.android.synthetic.main.topic_detail_activity.*
import retrofit2.Response

const val REPLY_TOPIC = 100

class ReplyTopicActivity() : AppCompatActivity(){

    private var topic: SingleTopicItem? = null

    private val mViewModel: ReplyTopicViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(ReplyTopicViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reply_topic_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        init()

        intent?.let {
            topic = it.getSerializableExtra("TOPIC") as SingleTopicItem
            mViewModel.showTopic(this, topic, posterImage, posterUsername, labelDate, labelTitle)
        }

        cancel_button.setOnClickListener(){
            finish()
        }

        reply_button.setOnClickListener(){
            enableLoading()
            if (isFormValid()){
                val form = PostModel(
                        topic_id = topic?.id?.toInt(),
                        raw = inputReplyTopic.text.toString()
                )
                mViewModel.replyTopic(object: DiscourseService.CallbackResponse<PostResponse>{
                    override fun onResponse(response: PostResponse) {
                        enableLoading(false)
                        setResult(REPLY_TOPIC)
                        finish()

                    }
                    override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {
                        enableLoading(false)
                        Snackbar.make(topic_create_container, "Error, Status code: $code", Snackbar.LENGTH_LONG).show()
                    }

                },  form = form)
            } else {
                enableLoading(false)
                showErrors()
            }
        }

    }

    fun init(){
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(this, R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(this, R.font.avenir_next_regular)
        toolbar_text.typeface = myCustomFontBold
        reply_button.typeface = myCustomFontRegular
        cancel_button.typeface = myCustomFontRegular
        inputReplyTopic.typeface = myCustomFontRegular
    }

    private fun isFormValid(): Boolean {
        return inputReplyTopic.text.isNotEmpty()
                && inputReplyTopic.text.length >= 20
    }

    private fun showErrors() {
        if (inputReplyTopic.text.isEmpty()) {
            inputReplyTopic.error = getString(R.string.error_empty)
            return
        }

        if (inputReplyTopic.text.length < 20) {
            inputReplyTopic.error = getString(R.string.error_reply_short)
            return
        }
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

}