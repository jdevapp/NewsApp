package com.newsapp.data.source.local

import com.newsapp.data.source.local.db.ArticlesDao
import com.newsapp.data.source.local.mapper.ArticleLocalMapper
import com.newsapp.domain.model.Article
import com.newsapp.result.Result
import javax.inject.Inject

/**
 * Concrete implementation of a data source as a db.
 */
class AppLocalDataSourceImpl @Inject constructor(
    private val articlesDao: ArticlesDao,
    private val articleMapper: ArticleLocalMapper
): AppLocalDataSource {

    override suspend fun getArticles(): Result<List<Article>> {
        return try{
            Result.Success(
                articleMapper.mapFromEntityList(articlesDao.getAll())
            )
        }catch(e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun insertArticles(articles: List<Article>) {
        try{
            val listOfArticleEntity = articleMapper.mapToEntityList(articles.toList())
            Result.Success(articlesDao.insert(*listOfArticleEntity.toTypedArray()))
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun deleteAllArticles() {
        try{
            articlesDao.deleteAll()
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

}