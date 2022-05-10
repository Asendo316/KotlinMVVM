package com.example.kotlinappmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kotlinappmvvm.databinding.UsersLayoutItemBinding
import com.example.kotlinappmvvm.db.favourites.FavouriteItemEntity
import com.example.kotlinappmvvm.fragments.FavouritesFragmentDirections

class FavouritesAdapter(): PagingDataAdapter<FavouriteItemEntity, FavouritesAdapter.FavouritesRowViewHolder>(DiffUtilCallback()) {

    inner class FavouritesRowViewHolder(val binding: UsersLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesRowViewHolder {
        return FavouritesRowViewHolder(
            UsersLayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }


    override fun onBindViewHolder(holder: FavouritesAdapter.FavouritesRowViewHolder, position: Int) {
        val currItem = getItem(position)
        holder.binding.apply {
            if (currItem != null) {
                name.text = currItem.login
                userType.text = currItem.type
                score.text = currItem.score.toString()
                imageView.load(currItem.avatar_url){
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }

        holder.itemView.setOnClickListener { mView->
            val direction = FavouritesFragmentDirections.actionFavouritesFragmentToFavouriteDetailFragment(
                currItem!!
            )
            mView.findNavController().navigate(direction)
        }
    }

    class  DiffUtilCallback: DiffUtil.ItemCallback<FavouriteItemEntity>() {
        override fun areItemsTheSame(oldFavouriteItem: FavouriteItemEntity, newFavouriteItem: FavouriteItemEntity): Boolean {
            return oldFavouriteItem == newFavouriteItem
        }

        override fun areContentsTheSame(oldFavouriteItem: FavouriteItemEntity, newFavouriteItem: FavouriteItemEntity): Boolean {
            return oldFavouriteItem.site_admin == newFavouriteItem.site_admin
                    && oldFavouriteItem.id == newFavouriteItem.id
        }

    }

}