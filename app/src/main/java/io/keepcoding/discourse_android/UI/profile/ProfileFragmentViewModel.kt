package io.keepcoding.discourse_android.UI.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.User
import io.keepcoding.discourse_android.Data.Models.ResponseModels.GetUserResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.GetUserTopicsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragmentViewModel(private val context: Application): ViewModel() {
    private var topicsPage: Int = 0

    fun getUser(id: Int, cb: DiscourseService.CallbackResponse<GetUserResponse>) {
        DiscourseService(context).discourseApi.fetchUser(id).enqueue(object: Callback<GetUserResponse> {
            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                if (response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable("error"), response, code = response.code())
                }
            }

            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }

    fun getUserTopics(username: String, cb: DiscourseService.CallbackResponse<GetUserTopicsResponse>) {
        DiscourseService(context).discourseApi.fetchUserTopics(username, topicsPage).enqueue(object: Callback<GetUserTopicsResponse> {
            override fun onResponse(
                call: Call<GetUserTopicsResponse>,
                response: Response<GetUserTopicsResponse>
            ) {
                if (response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable("error"), response, code = response.code())
                }
            }

            override fun onFailure(call: Call<GetUserTopicsResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }

    fun parseUser (response: GetUserResponse): User {
        return User.parse(response)
    }
}