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
){
    override fun toString(): String {
        return "Article(id=$id, source=$source, author=$author, title=$title, description=$description, url=$url, urlToImage=$urlToImage, publishedAt=$publishedAt, content=$content)"
    }
}