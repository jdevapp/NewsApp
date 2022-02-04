package com.newsapp.domain.model

data class TopHeadline (
    var status: String = "",
    var totalResults: Int = 0,
    var articles: List<Article>
)