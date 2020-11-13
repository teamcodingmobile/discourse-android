package io.keepcoding.discourse_android.UI.topics.topic_detail

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.SingleTopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SingleTopicResponse
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopicDetailViewModel(private val context: Application) : ViewModel()  {

    fun getSingleTopic(cb: DiscourseService.CallbackResponse<SingleTopicResponse>, topicId: String) {
        DiscourseService().discourseApi.fetchSingleTopic(topicId).enqueue(object : Callback<SingleTopicResponse> {
            override fun onResponse(call: Call<SingleTopicResponse>, response: Response<SingleTopicResponse>) {
                if (response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable("error"), response)
                }
            }
            override fun onFailure(call: Call<SingleTopicResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }

    fun parseSingleTopic (response: SingleTopicResponse): SingleTopicItem {
        return SingleTopicItem.parseTopic(response)
    }

    fun showTopic (context: Activity,topic: SingleTopicItem?, photo: AppCompatImageView, poster: TextView,
                   date: TextView, title: TextView, views: TextView, replies: TextView, posts: TextView, linearLayout: LinearLayout) {
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_regular)
        var utils = Utils()

        title.typeface = myCustomFontRegular
        posts.typeface = myCustomFontRegular
        views.typeface = myCustomFontRegular
        replies.typeface = myCustomFontRegular
        date.typeface = myCustomFontRegular
        poster.typeface = myCustomFontBold
        title.text = topic?.title.toString()
        posts.text = topic?.postCount.toString()
        views.text = topic?.views.toString()
        replies.text = topic?.replies.toString()
        linearLayout.setBackgroundColor(Color.parseColor("#F8F8F8"))
        poster.text = topic?.poster?.username.toString()
        var timeOffset = utils.getTimeOffset(topic!!.date)
        date.text = utils.setTimeOffset(timeOffset, context)

        Glide.with(context)
                .load(topic?.poster?.URL)
                .circleCrop()
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.placeholder)
                )
                .into(photo)
    }
}