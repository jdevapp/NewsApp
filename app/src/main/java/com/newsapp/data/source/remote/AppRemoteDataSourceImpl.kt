package com.newsapp.data.source.remote

import com.newsapp.data.source.remote.mapper.ArticleRemoteMapper
import com.newsapp.data.source.remote.service.DutchNewsService
import com.newsapp.domain.model.Article
import com.newsapp.result.Result
import com.newsapp.result.Result.*
import javax.inject.Inject

class AppRemoteDataSourceImpl @Inject constructor(
    private val dutchNewsService: DutchNewsService,
    private val articleRemoteMapper: ArticleRemoteMapper
): AppRemoteDataSource {

    override suspend fun getArticles(): Result<List<Article>> {
        return try {
            Success(
                articleRemoteMapper.mapFromEntityList(
                    dutchNewsService.getTopHeadlines().articles
                )
            )
        } catch (e: Exception) {
            Error(e)
        }
    }

}