package com.example.nownews.DataModel

data class NewsResponse(
    val articles: List<Article>,
    val totalArticles: Int
)