package com.newsapp.data.source.remote.mapper

import com.newsapp.data.source.remote.entity.ArticleResponse
import com.newsapp.domain.model.Article
import com.newsapp.data.util.EntityMapper
import javax.inject.Inject

class ArticleRemoteMapper @Inject constructor(
    private val sourceMapper: SourceRemoteMapper
): EntityMapper<ArticleResponse, Article> {

    override fun mapFromEntity(entity: ArticleResponse): Article {
        return Article(
            source = sourceMapper.mapFromEntity(entity.source),
            author = entity.author,
            title = entity.title,
            description = entity.description,
            url = entity.url,
            urlToImage = entity.urlToImage,
            publishedAt = entity.publishedAt,
            content = entity.content
        )
    }
    fun mapFromEntityList(entities: List<ArticleResponse>?): List<Article>{
        if(entities == null){
            return emptyList()
        }
        return entities.map { mapFromEntity(it) }
    }
}
