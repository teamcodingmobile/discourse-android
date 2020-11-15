package io.keepcoding.discourse_android.Data.Client.Http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class DiscourseService {

    interface CallbackResponse<T> {
        fun onResponse(response: T)
        fun onFailure(t: Throwable, res: Response<*>? = null)
    }

    val discourseApi: DiscourseApi

    init {

        val timeout: Long = 6 * 1000

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)


        val client = OkHttpClient.Builder().apply{
            addInterceptor(
                    Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        builder.header("Api-Key", API_KEY)
                        builder.header("Api-Username", API_USERNAME)
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

