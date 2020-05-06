package com.newsfeed.data

data class DetailsList(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)