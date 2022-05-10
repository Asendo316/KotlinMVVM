package com.example.kotlinappmvvm.network

import com.example.kotlinappmvvm.helper.Constants.END_POINT
import com.example.kotlinappmvvm.models.GithubUserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(END_POINT)
    suspend fun getGithubUsers(@Query(value = "q") q: String, @Query(value = "page") page: String)
            : Response<GithubUserListResponse>

}