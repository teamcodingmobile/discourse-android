package io.keepcoding.discourse_android.UI.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.User
import io.keepcoding.discourse_android.Data.Models.ResponseModels.GetUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragmentViewModel(private val context: Application): ViewModel() {
    fun getUser(id: Int, cb: DiscourseService.CallbackResponse<GetUserResponse>) {
        DiscourseService(context).discourseApi.getUser(id).enqueue(object: Callback<GetUserResponse> {
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

    fun parseUser (response: GetUserResponse): User {
        return User.parse(response)
    }
}