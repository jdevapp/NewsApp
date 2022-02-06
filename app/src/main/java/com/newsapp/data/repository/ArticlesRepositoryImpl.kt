package com.newsapp.data.repository

import com.newsapp.data.source.local.AppLocalDataSource
import com.newsapp.data.source.remote.AppRemoteDataSource
import com.newsapp.domain.model.Article
import com.newsapp.domain.repository.ArticlesRepository
import com.newsapp.result.Result
import com.newsapp.result.Result.Error
import javax.inject.Inject

/**
 * implementation of [ArticlesRepository]. Single entry point for managing articles data.
 */

class ArticlesRepositoryImpl @Inject constructor(
    private val remoteDataSource: AppRemoteDataSource,
    private val localDataSource: AppLocalDataSource
) : ArticlesRepository {

    override suspend fun getArticles(forceUpdate: Boolean): Result<List<Article>> {
        return try {
            if(forceUpdate){
                updateArticlesFromRemoteDataSource()
            }
            localDataSource.getArticles()
        } catch (ex: Exception) {
            Error(ex)
        }
    }
    override suspend fun refreshArticles() {
        updateArticlesFromRemoteDataSource()
    }
    private suspend fun updateArticlesFromRemoteDataSource() {
        val remoteArticles = remoteDataSource.getArticles()

        if (remoteArticles is Result.Success) {
            localDataSource.deleteAllArticles()
            localDataSource.insertArticles(remoteArticles.data)
        } else if (remoteArticles is Error) {
            throw remoteArticles.exception
        }
    }

}
