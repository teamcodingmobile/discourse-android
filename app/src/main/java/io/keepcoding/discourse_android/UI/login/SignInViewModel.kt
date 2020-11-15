package io.keepcoding.discourse_android.UI.login

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.ResetPasswordModel
import io.keepcoding.discourse_android.Data.Models.ResponseModels.ResetPasswordResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel(private val context: Application) : ViewModel()  {
    fun login(cb: DiscourseService.CallbackResponse<SignInResponse>, username: String) {
        DiscourseService().discourseApi.signIn(username).enqueue(object : Callback<SignInResponse> {
            override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                if (response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable("error"), response, code = response.code())
                }
            }
            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                cb.onFailure(t)
            }

        })
    }

    fun resetPassword(cb: DiscourseService.CallbackResponse<ResetPasswordResponse>, form: ResetPasswordModel) {
        DiscourseService().discourseApi.resetPassword(form).enqueue(object : Callback<ResetPasswordResponse> {
            override fun onResponse(call: Call<ResetPasswordResponse>, response: Response<ResetPasswordResponse>) {
                if (response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable("error"), code = response.code())
                }
            }
            override fun onFailure(call: Call<ResetPasswordResponse>, t: Throwable) {
                cb.onFailure(t)
            }

        })
    }
}