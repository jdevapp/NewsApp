package com.newsapp.domain.model

import java.util.*

data class Article (
    var id: Long? = null,
    var source: String? = "",
    var author: String? = "",
    var title: String? = "",
    var description: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    var publishedAt: Date? = null,
    var content: String? = ""
)