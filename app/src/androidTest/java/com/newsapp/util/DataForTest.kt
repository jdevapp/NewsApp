package com.newsapp.util

import com.newsapp.domain.model.Article
import java.util.*

/**
 * Test data for unit tests.
 */
object Data {

    val article1 = Article(
        source = "source 1",
        author = "author 1",
        title = "title 1",
        description ="description 1",
        url ="url 1",
        urlToImage = "urlToImage 1",
        publishedAt = Date(),
        content = "content 1"
    )
    val article2 = Article(
        source = "article 2",
        author = "author 2",
        title = "title 2",
        description ="description 2",
        url ="url 2",
        urlToImage = "urlToImage 2",
        publishedAt = Date(),
        content = "content 2"
    )

    val articles = listOf(
        article1,
        article2
    )

}