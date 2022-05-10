package com.example.kotlinappmvvm.db.cache

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheAppDao {

    @Query("SELECT * FROM usersCache ORDER BY id DESC")
    fun getAllRecords(): Flow<List<CacheItemEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(cacheItemEntity: List<CacheItemEntity>)

    @Query("DELETE FROM usersCache")
    fun deleteRecord()
}