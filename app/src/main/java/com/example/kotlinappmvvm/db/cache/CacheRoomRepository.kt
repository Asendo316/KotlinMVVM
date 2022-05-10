package com.example.kotlinappmvvm.db.cache

import androidx.paging.PagingSource
import com.example.kotlinappmvvm.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class CacheRoomRepository
@Inject constructor(private val cacheAppDao: CacheAppDao) {

    fun getAllRecords() {
        val cache =  cacheAppDao.getAllRecords()

        /*fun getAllRecords() = networkBoundResource(
            query = {
                restaurantDao.getAllRestaurants()
            },
            fetch = {
                delay(2000)
                api.getRestaurants()
            },
            saveFetchResult = { restaurants ->
                db.withTransaction {
                    cacheAppDao.deleteRecord()
                    cacheAppDao.insertRecord(restaurants)
                }
            }
        )*/
    }

    fun insertRecord(cacheItemEntity: List<CacheItemEntity>) {
        cacheAppDao.insertRecord(cacheItemEntity)
    }

    fun deleteRecord() {
        cacheAppDao.deleteRecord()
    }
}