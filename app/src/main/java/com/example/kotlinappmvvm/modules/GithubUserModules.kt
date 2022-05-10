package com.example.kotlinappmvvm.modules

import com.example.kotlinappmvvm.helper.Constants.BASE_URL
import com.example.kotlinappmvvm.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubUserModules {

    @Provides
    @Singleton
    fun provideRetrofitInstance():ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}