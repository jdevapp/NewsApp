package com.newsapp.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.newsapp.data.source.local.entity.SourceEntity

/**
 * Data Access Object for the [SourceEntity] class.
 */
@Dao
interface SourcesDao {

    @Query("SELECT * FROM sources")
    suspend fun getAll(): List<SourceEntity>

    @Query("SELECT * FROM sources WHERE id = :id")
    suspend fun find(id: String): SourceEntity?

    @Insert
    suspend fun insert(source: SourceEntity): Long

    @Update
    suspend fun update(source: SourceEntity)

}