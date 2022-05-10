package com.example.kotlinappmvvm.db.favourites

import androidx.paging.PagingSource
import javax.inject.Inject

class FavouriteRoomRepository
@Inject constructor(private val favouriteAppDao: FavouriteAppDao) {

    fun getAllRecords(): PagingSource<Int, FavouriteItemEntity> {
        return favouriteAppDao.getAllRecords()
    }

    fun insertRecord(favouriteItemEntity: FavouriteItemEntity) {
        favouriteAppDao.insertRecord(favouriteItemEntity)
    }

    fun deleteRecord(id: Int) {
        favouriteAppDao.deleteRecord(id)
    }
}