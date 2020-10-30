package io.keepcoding.discourse_android.network

import io.keepcoding.discourse_android.models.LatestTopicsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface DiscourseApi {
    @GET ("latest")
    @Headers("Content-Type: application/json")
    //TODO: reemplazar object con el modelo de respuesta
    fun getResponse() : Call <LatestTopicsResponse>
}