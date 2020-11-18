package io.keepcoding.discourse_android.UI.topics

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.TopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.LatestTopicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopicsFragmentViewModel(private val context: Application) : ViewModel() {


    fun getTopics(cb: DiscourseService.CallbackResponse<LatestTopicResponse>, page: Int = 0) {
        DiscourseService(context).discourseApi.fetchTopics(atPage = page).enqueue(object : Callback<LatestTopicResponse> {
            override fun onResponse(call: Call<LatestTopicResponse>, response: Response<LatestTopicResponse>) {
                if (response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable("error"), response, code = response.code())
                }
            }
            override fun onFailure(call: Call<LatestTopicResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }

    fun parseTopics (response: LatestTopicResponse): MutableList<TopicItem>? {
        return TopicItem.parseTopicsList(response)
    }
}