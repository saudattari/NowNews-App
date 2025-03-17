package com.example.nownews

import com.example.nownews.Interface.NewsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
        val api: NewsApiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://gnews.io/api/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApiService::class.java)
        }
    }
