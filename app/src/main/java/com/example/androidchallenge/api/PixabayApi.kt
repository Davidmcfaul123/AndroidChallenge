package com.example.androidchallenge.api

import com.example.androidchallenge.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PixabayApi {

    companion object {
        const val BASE_URL = "https://pixabay.com/api/"
        const val CLIENT_ID = BuildConfig.PIXABAY_ACCESS_KEY
    }
    @Headers("Authorisation: Client-ID $CLIENT_ID")
    @GET("https://pixabay.com/api/")
    suspend fun searchPhotos(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        ): PixabayResponse
}