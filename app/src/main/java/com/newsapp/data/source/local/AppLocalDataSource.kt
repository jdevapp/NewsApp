package com.newsapp.data.source.local

import com.newsapp.domain.model.Article
import com.newsapp.result.Result

/**
 * Main entry point for accessing articles data.
 */
interface AppLocalDataSource {
    suspend fun getArticles(): Result<List<Article>>
    suspend fun insertArticles(articles: List<Article>)
    suspend fun deleteAllArticles()
}