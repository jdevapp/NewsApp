package com.newsapp.data.source.remote.mapper

import com.newsapp.data.source.remote.entity.SourceResponse
import com.newsapp.domain.model.Source
import com.newsapp.data.util.EntityMapper
import javax.inject.Inject

class SourceRemoteMapper @Inject constructor(
): EntityMapper<SourceResponse, Source> {

    override fun mapFromEntity(entity: SourceResponse): Source {
        return Source(
            id = entity.id,
            name = entity.name
        )
    }

    fun mapFromEntityList(entities: List<SourceResponse>?): List<Source>{
        if(entities == null){
            return emptyList()
        }
        return entities.map { mapFromEntity(it) }
    }
}
