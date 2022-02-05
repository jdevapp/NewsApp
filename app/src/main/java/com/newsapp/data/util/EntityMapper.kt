package com.newsapp.data.util

interface EntityMapper <Entity, DomainModel>{
    fun mapFromEntity(entity: Entity): DomainModel
}
