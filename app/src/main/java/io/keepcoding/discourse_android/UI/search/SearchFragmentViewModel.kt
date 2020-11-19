package io.keepcoding.discourse_android.UI.search

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragmentViewModel(private val context: Application) : ViewModel() {

    fun getSearch(withWord: String, cb: DiscourseService.CallbackResponse<SearchResponse>) {
        DiscourseService(context).discourseApi.searchByWord(withWord).enqueue(object : Callback<SearchResponse> {

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {

                if (response.body() != null) {
                    cb.onResponse(response.body()!!)

                } else {
                    cb.onFailure(Throwable(response.message()), response)
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }

}