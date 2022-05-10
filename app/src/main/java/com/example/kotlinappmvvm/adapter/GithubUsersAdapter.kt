package com.example.kotlinappmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kotlinappmvvm.databinding.UsersLayoutItemBinding
import com.example.kotlinappmvvm.models.Item


class GithubUsersAdapter : RecyclerView.Adapter<GithubUsersAdapter.GithubUsersViewHolder>() {

    inner class GithubUsersViewHolder(val binding: UsersLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var users: List<Item>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUsersViewHolder {
        return GithubUsersViewHolder(
            UsersLayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }




    override fun onBindViewHolder(holder: GithubUsersViewHolder, position: Int) {
        val currUser = users[position]
        holder.binding.apply {
            name.text = currUser.login
            userType.text = currUser.type
            score.text = currUser.score.toString()
            imageView.load(currUser.avatar_url){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount(): Int {
        return users.size;
    }
}