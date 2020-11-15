package io.keepcoding.discourse_android.UI.topics.topic_detail.reply

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
import io.keepcoding.discourse_android.Data.Models.AppModels.PostModel
import io.keepcoding.discourse_android.Data.Models.AppModels.SingleTopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.PostResponse
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReplyTopicViewModel(private val context: Application) : ViewModel()  {
    fun replyTopic(cb: DiscourseService.CallbackResponse<PostResponse>, form: PostModel) {
        DiscourseService(context).discourseApi.replyTopic(form).enqueue(object: Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable("error"), response, code = response.code())
                }
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }

    fun showTopic (context: Activity, topic: SingleTopicItem?, photo: AppCompatImageView, poster: TextView,
                   date: TextView, title: TextView) {
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_regular)
        var utils = Utils()

        title.typeface = myCustomFontBold
        date.typeface = myCustomFontRegular
        poster.typeface = myCustomFontBold
        title.text = topic?.title.toString()
        poster.text = topic?.poster?.username.toString()
        var timeOffset = utils.getTimeOffset(topic!!.date)
        date.text = utils.setTimeOffset(timeOffset, context)

        Glide.with(context)
                .load(topic.poster?.URL)
                .circleCrop()
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.placeholder)
                )
                .into(photo)
    }

}