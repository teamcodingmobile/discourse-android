package io.keepcoding.discourse_android.Data.Client.Http

import io.keepcoding.discourse_android.Data.Models.ResponseModels.LatestTopicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface DiscourseApi {
    @GET ("latest.json")
    @Headers("Content-Type: application/json")
    fun fetchTopics() : Call<LatestTopicResponse>
}