package com.newsapp.data.source.remote.mapper

import com.newsapp.data.source.remote.entity.TopHeadlineResponse
import com.newsapp.domain.model.TopHeadline
import com.newsapp.data.util.EntityMapper
import javax.inject.Inject

class TopHeadlineRemoteMapper @Inject constructor(
    private val articleMapper: ArticleRemoteMapper
): EntityMapper<TopHeadlineResponse, TopHeadline> {

    override fun mapFromEntity(entity: TopHeadlineResponse): TopHeadline {
        return TopHeadline(
            status = entity.status,
            totalResults = entity.totalResults,
            articles = articleMapper.mapFromEntityList(entity.articles)
        )
    }

    fun mapFromEntityList(entities: List<TopHeadlineResponse>?): List<TopHeadline>{
        if(entities == null){
            return emptyList()
        }
        return entities.map { mapFromEntity(it) }
    }
}
