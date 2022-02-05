package com.newsapp.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.newsapp.data.source.local.entity.ArticleEntity
import com.newsapp.data.util.DateConverter

@Database(
    entities = arrayOf(ArticleEntity::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao
}