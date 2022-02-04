package com.newsapp.data.source.remote.entity

data class TopHeadlineResponse (
    var status: String = "",
    var totalResults: Int = 0,
    var articles: List<ArticleResponse>
)