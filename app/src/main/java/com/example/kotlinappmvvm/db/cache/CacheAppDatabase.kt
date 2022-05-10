package com.example.kotlinappmvvm.db.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CacheItemEntity::class], version = 1, exportSchema = false)
abstract class CacheAppDatabase: RoomDatabase() {

    abstract fun getAppDao(): CacheAppDao

    companion object {
        private var dbInstance: CacheAppDatabase? = null

        fun getAppDbInstance(context: Context): CacheAppDatabase {
            if(dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context.applicationContext, CacheAppDatabase::class.java, "MYDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return dbInstance!!
        }

    }
}