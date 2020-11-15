package io.keepcoding.discourse_android.Data.Client.Http

import io.keepcoding.discourse_android.BuildConfig
import io.keepcoding.discourse_android.Data.Models.AppModels.SignUpModel
import io.keepcoding.discourse_android.Data.Models.ResponseModels.LatestTopicResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SignUpResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SingleTopicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

const val API_KEY = "699667f923e65fac39b632b0d9b2db0d9ee40f9da15480ad5a4bcb3c1b095b7a"
const val API_USERNAME = "system"

interface DiscourseApi {
    @GET ("latest.json")
    @Headers("Content-Type: application/json")
    fun fetchTopics() : Call<LatestTopicResponse>

    @GET ("t/{topicId}.json")
    @Headers("Content-Type: application/json")
    fun fetchSingleTopic(@Path("topicId") topicId: String) : Call<SingleTopicResponse>

    @POST("users")
    @Headers("Content-Type: application/json")
    fun createUser(@Body body: SignUpModel, @Query("Api-Key") apiKey: String = API_KEY,
                   @Query("Api-Username") user: String = API_USERNAME): Call <SignUpResponse>



}