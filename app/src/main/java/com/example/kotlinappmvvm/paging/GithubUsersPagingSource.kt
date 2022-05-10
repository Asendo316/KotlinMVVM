package com.example.kotlinappmvvm.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlinappmvvm.helper.Constants
import com.example.kotlinappmvvm.models.Item
import com.example.kotlinappmvvm.network.ApiService

class GithubUsersPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Item> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getGithubUsers(Constants.LOCATION, currentPage.toString())
            val responseData = mutableListOf<Item>()
            val data = response.body()?.items ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}
