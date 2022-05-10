package com.example.kotlinappmvvm.modules

import android.app.Application
import com.example.kotlinappmvvm.db.favourites.FavouriteAppDao
import com.example.kotlinappmvvm.db.favourites.FavouriteAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavouritesModule {

    @Singleton
    @Provides
    fun getAppDatabase(context: Application): FavouriteAppDatabase {
        return FavouriteAppDatabase.getAppDbInstance(context)
    }

    @Singleton
    @Provides
    fun appDao(favouriteAppDatabase: FavouriteAppDatabase): FavouriteAppDao {
        return favouriteAppDatabase.getAppDao()
    }
}