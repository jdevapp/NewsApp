package com.newsapp.domain.repository

import com.newsapp.domain.model.Article
import com.newsapp.result.Result

/**
 * Interface to the data layer.
 */

interface ArticlesRepository {
    /**
     * Returns a list of [Article]s. When the parameter is passed as true,
     * it's guaranteed the data loaded from this use case is up to date with the
     * remote data source
     */
    suspend fun getArticles(forceUpdate: Boolean = true): Result<List<Article>>
    suspend fun refreshArticles()

}