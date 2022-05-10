package com.example.kotlinappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlinappmvvm.db.favourites.FavouriteItemEntity
import com.example.kotlinappmvvm.db.favourites.FavouriteRoomRepository
import com.example.kotlinappmvvm.models.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel
@Inject
constructor(
    private val repositoryFavourite: FavouriteRoomRepository
    ) : ViewModel() {
    fun getAllRecords(): Flow<PagingData<FavouriteItemEntity>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { repositoryFavourite.getAllRecords() }).flow.cachedIn(viewModelScope)
    }


    fun insertCurrentRecord(item: Item) {
        repositoryFavourite.insertRecord(
            FavouriteItemEntity(
                0,
                item.avatar_url,
                item.events_url,
                item.followers_url,
                item.following_url,
                item.gists_url,
                item.gravatar_id,
                item.html_url,
                item.login,
                item.node_id,
                item.organizations_url,
                item.received_events_url,
                item.repos_url,
                item.score,
                item.site_admin,
                item.starred_url,
                item.subscriptions_url,
                item.type,
                item.url
            )
        )
    }

    fun removeCurrentRecord(id: Int){
        repositoryFavourite.deleteRecord(id)
    }
}