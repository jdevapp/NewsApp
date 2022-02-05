package com.newsapp.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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
    suspend fun insert(article: ArticleEntity): Long

    @Update
    suspend fun update(article: ArticleEntity)

}