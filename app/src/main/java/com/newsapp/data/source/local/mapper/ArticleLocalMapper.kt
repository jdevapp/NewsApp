package com.newsapp.data.source.local.mapper

import com.newsapp.data.source.local.entity.ArticleEntity
import com.newsapp.data.util.EntityMapper
import com.newsapp.domain.model.Article
import javax.inject.Inject

class ArticleLocalMapper @Inject constructor(

): EntityMapper<ArticleEntity, Article> {

    override fun mapFromEntity(entity: ArticleEntity): Article {
        return Article(
            id = entity.articleId,
            source = entity.sourceName,
            author = entity.author,
            title = entity.title,
            description = entity.description,
            url = entity.url,
            urlToImage = entity.urlToImage,
            publishedAt = entity.publishedAt,
            content = entity.content
        )
    }

    override fun mapToEntity(domainModel: Article): ArticleEntity {
        return ArticleEntity(
            sourceName = domainModel.source!!,
            author = domainModel.author,
            title = domainModel.title,
            description = domainModel.description,
            url = domainModel.url,
            urlToImage = domainModel.urlToImage,
            publishedAt = domainModel.publishedAt,
            content = domainModel.content,
        )
    }

    fun mapFromEntityList(entities: List<ArticleEntity>): List<Article>{
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(articles: List<Article>): List<ArticleEntity>{
        return articles.map { mapToEntity(it) }
    }

}
