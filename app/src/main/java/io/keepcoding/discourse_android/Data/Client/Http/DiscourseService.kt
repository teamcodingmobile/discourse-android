package io.keepcoding.discourse_android.Data.Client.Http

import android.content.Context
import io.keepcoding.discourse_android.Data.LoginService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY = "699667f923e65fac39b632b0d9b2db0d9ee40f9da15480ad5a4bcb3c1b095b7a"
const val API_USERNAME = "system"
const val CONTENT_TYPE = "Content-Type: application/json"


class DiscourseService(context: Context) {

    interface CallbackResponse<T> {
        fun onResponse(response: T)
        fun onFailure(t: Throwable, res: Response<*>? = null, code: Int = 0)
    }

    val discourseApi: DiscourseApi
    val loginService = LoginService()

    init {

        val timeout: Long = 6 * 1000

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)


        val client = OkHttpClient.Builder().apply{
            addInterceptor(
                    Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        builder.header("Api-Key", API_KEY)
                        builder.header("Api-Username", loginService.getUsername(context) ?: API_USERNAME)
                        builder.header("Content-Type", CONTENT_TYPE)
                        return@Interceptor chain.proceed(builder.build())
                    }
            )
        }
            .addInterceptor(logging)

            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .build()


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://mdiscourse.keepcoding.io")
            .client(client)
            .build()

        discourseApi = retrofit.create(DiscourseApi::class.java)
    }
}

