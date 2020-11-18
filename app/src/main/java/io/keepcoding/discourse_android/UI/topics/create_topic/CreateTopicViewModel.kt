package io.keepcoding.discourse_android.UI.topics.create_topic

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.PostModel
import io.keepcoding.discourse_android.Data.Models.AppModels.SignUpModel
import io.keepcoding.discourse_android.Data.Models.ResponseModels.PostResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateTopicViewModel(private val context: Application) : ViewModel()  {
    fun createTopic(cb: DiscourseService.CallbackResponse<PostResponse>, form: PostModel) {
        DiscourseService(context).discourseApi.createTopic(form).enqueue(object: Callback<PostResponse> {
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

}