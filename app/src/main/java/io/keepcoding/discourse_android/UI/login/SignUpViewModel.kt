package io.keepcoding.discourse_android.UI.login

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.SignUpModel
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(private val context: Application) : ViewModel()  {

    fun signUp(cb: DiscourseService.CallbackResponse<SignUpResponse>, body: SignUpModel) {
        DiscourseService().discourseApi.createUser(body = body).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable("error"), response)
                }
            }
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }


}