package com.example.nownews.Interface


import com.example.nownews.DataModel.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.jvm.java

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String = "general",
        @Query("country") country: String = "pk",
        @Query("token") apiKey: String,
        @Query("q") query:String = ""
    ): NewsResponse

}