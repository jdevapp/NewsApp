package com.newsapp.data.source.local.db

import androidx.room.*
import com.newsapp.data.source.local.entity.ArticleEntity

/**
 * Data Access Object for the [ArticleEntity] class.
 */
@Dao
interface ArticlesDao {

    @Query("SELECT * FROM articles")
    suspend fun getAll(): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE articleId = :id")
    suspend fun find(id: String): ArticleEntity?

    @Insert
    suspend fun insert(vararg article: ArticleEntity)

    @Update
    suspend fun update(article: ArticleEntity)

    @Query("DELETE from articles")
    suspend fun deleteAll()

}