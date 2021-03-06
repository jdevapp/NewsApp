package com.newsapp.data.source.remote.mapper

import com.newsapp.data.source.remote.entity.ArticleResponse
import com.newsapp.domain.model.Article
import com.newsapp.data.util.EntityMapper
import com.newsapp.util.DateTimeUtils.fromString
import javax.inject.Inject

class ArticleRemoteMapper @Inject constructor(

): EntityMapper<ArticleResponse, Article> {

    override fun mapFromEntity(entity: ArticleResponse): Article {
        return Article(
            source = entity.source?.name,
            author = entity.author,
            title = entity.title,
            description = entity.description,
            url = entity.url,
            urlToImage = entity.urlToImage,
            publishedAt = fromString(entity.publishedAt),
            content = entity.content
        )
    }
    override fun mapToEntity(domainModel: Article): ArticleResponse {
        return ArticleResponse()
    }
    fun mapFromEntityList(entities: List<ArticleResponse>?): List<Article>{
        if(entities == null){
            return emptyList()
        }
        return entities.map { mapFromEntity(it) }
    }

}
