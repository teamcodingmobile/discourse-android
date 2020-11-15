package io.keepcoding.discourse_android.Data.Client.Http

import io.keepcoding.discourse_android.Data.Models.AppModels.SignUpModel
import io.keepcoding.discourse_android.Data.Models.ResponseModels.LatestTopicResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SignInResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SignUpResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SingleTopicResponse
import retrofit2.Call
import retrofit2.http.*



interface DiscourseApi {
    @POST("users")
    fun createUser(@Body body: SignUpModel) : Call <SignUpResponse>

    @GET ("users/{username}.json")
    fun signIn(@Path("username") username: String) : Call<SignInResponse>

    @GET ("latest.json")
    fun fetchTopics() : Call<LatestTopicResponse>

    @GET ("t/{topicId}.json")
    fun fetchSingleTopic(@Path("topicId") topicId: String) : Call<SingleTopicResponse>
}