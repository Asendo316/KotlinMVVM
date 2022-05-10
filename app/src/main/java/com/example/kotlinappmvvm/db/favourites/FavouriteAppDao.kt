package com.example.kotlinappmvvm.db.favourites

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteAppDao {

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAllRecords(): PagingSource<Int, FavouriteItemEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(favouriteItemEntity: FavouriteItemEntity)

    @Query("DELETE FROM user WHERE id = :id")
    fun deleteRecord(id: Int)
}