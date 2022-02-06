package com.newsapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "articles")
data class ArticleEntity (
    @PrimaryKey(autoGenerate = true) val articleId : Long? = null,
    @ColumnInfo(name = "source_id") val sourceId: String? = "",
    @ColumnInfo(name = "source_name") val sourceName: String? = "",
    @ColumnInfo(name = "author") val author: String? = "",
    @ColumnInfo(name = "title") val title: String? = "",
    @ColumnInfo(name = "description") val description: String? = "",
    @ColumnInfo(name = "url") val url: String? = "",
    @ColumnInfo(name = "url_to_image") val urlToImage: String? = "",
    @ColumnInfo(name = "published_at") val publishedAt: Date? = null,
    @ColumnInfo(name = "content") val content: String? = ""
)