package io.keepcoding.discourse_android.Data.Client.Http

import io.keepcoding.discourse_android.Data.Models.ResponseModels.LatestTopicResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SingleTopicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface DiscourseApi {
    @GET ("latest.json")
    @Headers("Content-Type: application/json")
    fun fetchTopics() : Call<LatestTopicResponse>

    @GET ("t/{topicId}.json")
    @Headers("Content-Type: application/json")
    fun fetchSingleTopic(@Path("topicId") topicId: String) : Call<SingleTopicResponse>

}