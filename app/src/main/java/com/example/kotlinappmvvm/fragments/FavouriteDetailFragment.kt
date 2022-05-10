package com.example.kotlinappmvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.kotlinappmvvm.R
import com.example.kotlinappmvvm.databinding.FragmentFavouriteDetailBinding
import com.example.kotlinappmvvm.db.favourites.FavouriteItemEntity
import com.example.kotlinappmvvm.viewmodel.FavouritesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteDetailFragment : Fragment(R.layout.fragment_favourite_detail) {

    private var _binding : FragmentFavouriteDetailBinding? = null
    private val  binding get() = _binding!!
    private val args: FavouriteDetailFragmentArgs by navArgs()
    private lateinit var favouriteItem: FavouriteItemEntity
    private val viewModel: FavouritesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteDetailBinding.inflate(
            inflater,container,false
        )
        return binding
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favouriteItem = args.item

        populateUI()

        val fav: Button = binding.addFav

        fav.setOnClickListener { view ->
            viewModel.removeCurrentRecord(favouriteItem.id)
            Snackbar.make(view, "Removed from Favourites", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun populateUI() {
        binding.apply {
            login.text = "User : ${favouriteItem.login}"
            id.text = "ID : ${favouriteItem.id}"
            nodeId.text = "Node ID : ${favouriteItem.node_id}"
            type.text = "Item Type : ${favouriteItem.type}"
            siteAdmin.text = "Admin : ${favouriteItem.site_admin}"
            score.text = "Score : ${favouriteItem.score}"
            image.load(favouriteItem.avatar_url) {
                crossfade(true)
                crossfade(1000)
            }

            /*         OUT OF SCOPE
                       url.text = item.url
                       htmlUrl.text = item.html_url
                       followersUrl.text = item.followers_url
                       gistsUrl.text = item.gists_url
                       starredUrl.text = item.starred_url
                       subscriptionsUrl.text = item.subscriptions_url
                       organizationsUrl.text = item.organizations_url
                       reposUrl.text = item.repos_url
                       eventUrl.text = item.events_url*/


            /*btnOpenWebView.setOnClickListener { mView ->

                val directions = DetailFragmentDirections
                    .actionDetailFragment2ToWebViewFragment(recipe)

                mView.findNavController().navigate(directions)

            }*/

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}