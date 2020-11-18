package io.keepcoding.discourse_android.Data.Client.Http

import io.keepcoding.discourse_android.Data.Models.AppModels.PostModel
import io.keepcoding.discourse_android.Data.Models.AppModels.ResetPasswordModel
import io.keepcoding.discourse_android.Data.Models.AppModels.SignUpModel
import io.keepcoding.discourse_android.Data.Models.ResponseModels.*
import retrofit2.Call
import retrofit2.http.*



interface DiscourseApi {
    @POST("users")
    fun createUser(@Body body: SignUpModel) : Call <SignUpResponse>

    @GET("admin/users/{id}.json")
    @Headers( "Api-Username: system")
    fun fetchUser(@Path("id") id: Int): Call<GetUserResponse>

    @GET ("users/{username}.json")
    fun signIn(@Path("username") username: String) : Call<SignInResponse>

    @GET ("latest.json")
    fun fetchTopics(@Query("page") atPage: Int = 0) : Call<LatestTopicResponse>

    @GET ("/topics/created-by/{username}.json")
    fun fetchUserTopics(@Path("username") username: String, @Query("page") page: Int): Call<GetUserTopicsResponse>

    @GET ("t/{topicId}.json")
    fun fetchSingleTopic(@Path("topicId") topicId: String) : Call<SingleTopicResponse>

    @POST("session/forgot_password")
    fun resetPassword(@Body body: ResetPasswordModel) : Call <ResetPasswordResponse>

    @GET("search/query.json")
    fun searchByWord(@Query ("term") term:String): Call<SearchResponse>

    @POST ("posts.json")
    fun createTopic(@Body body: PostModel) : Call <PostResponse>

    @POST ("posts.json")
    fun replyTopic(@Body body: PostModel) : Call <PostResponse>

}