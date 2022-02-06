package com.newsapp.data.source.remote.entity

data class ArticleResponse (
    var source: SourceResponse? = null,
    var author: String? = "",
    var title: String? = "",
    var description: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    var publishedAt: String? = "",
    var content: String? = ""
)