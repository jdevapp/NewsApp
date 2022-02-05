package com.newsapp.data.source.remote

import com.newsapp.domain.model.Article
import com.newsapp.result.Result

/**
 * Main entry point for accessing articles data.
 */
interface AppRemoteDataSource {
    suspend fun getArticles(): Result<List<Article>>
}