package com.example.kotlinappmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.kotlinappmvvm.databinding.UsersLayoutItemBinding
import com.example.kotlinappmvvm.fragments.HomeFragment
import com.example.kotlinappmvvm.fragments.HomeFragmentDirections
import com.example.kotlinappmvvm.models.Item

class GithubUsersPagingAdapter : PagingDataAdapter<Item,
        GithubUsersPagingAdapter.GithubUsersViewHolder>(diffCallback) {


    inner class GithubUsersViewHolder(
        val binding: UsersLayoutItemBinding
    ) :
        ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUsersViewHolder {
        return GithubUsersViewHolder(
            UsersLayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GithubUsersViewHolder, position: Int) {
        val currUser = getItem(position)

        holder.binding.apply {
            name.text = currUser?.login
            userType.text = currUser?.type
            score.text = currUser?.score.toString()
            imageView.load(currUser?.avatar_url){
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener { mView->
            val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(currUser!!)
            mView.findNavController().navigate(direction)
        }

    }


}