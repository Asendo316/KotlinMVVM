package com.example.kotlinappmvvm.db.favourites

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteItemEntity::class], version = 1, exportSchema = false)
abstract class FavouriteAppDatabase: RoomDatabase() {

    abstract fun getAppDao(): FavouriteAppDao

    companion object {
        private var dbInstance: FavouriteAppDatabase? = null

        fun getAppDbInstance(context: Context): FavouriteAppDatabase {
            if(dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context.applicationContext, FavouriteAppDatabase::class.java, "MYDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return dbInstance!!
        }

    }
}