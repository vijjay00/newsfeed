package com.newsfeed.utils

import com.newsfeed.data.DetailsList
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MyApi {

    @GET("top-headlines")
    fun fetchdetails(@Query("country") country: String?,@Query("category") category: String?,
                     @Query("apiKey") id: String?): Call<DetailsList>

    companion object{
        operator fun invoke(): MyApi {

            val okkHttpclient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("http://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}