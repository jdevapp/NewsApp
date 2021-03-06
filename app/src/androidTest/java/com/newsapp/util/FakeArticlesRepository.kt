package com.newsapp.util

import com.google.gson.Gson
import com.newsapp.R
import com.newsapp.data.source.remote.entity.TopHeadlineResponse
import com.newsapp.data.source.remote.mapper.ArticleRemoteMapper
import com.newsapp.domain.model.Article
import com.newsapp.domain.repository.ArticlesRepository
import com.newsapp.result.Result

class FakeArticlesRepository: ArticlesRepository {

    override suspend fun getArticles(forceUpdate: Boolean): Result<List<Article>> {
        val articlesEntity =
            Gson().fromJson(Util.getJson(R.raw.data), TopHeadlineResponse::class.java).articles
        return Result.Success(
            ArticleRemoteMapper().mapFromEntityList(articlesEntity)
        )
    }

    override suspend fun refreshArticles() {}
}