package com.example.kotlinappmvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinappmvvm.R
import com.example.kotlinappmvvm.adapter.GithubUsersPagingAdapter
import com.example.kotlinappmvvm.databinding.FragmentHomeBinding
import com.example.kotlinappmvvm.viewmodel.GithubUsersViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GithubUsersViewModel by viewModels()
    private lateinit var usersAdapter: GithubUsersPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )
        return binding
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadData()

        val fab: FloatingActionButton = binding.fab

        fab.setOnClickListener { view ->
            val direction = HomeFragmentDirections.actionHomeFragmentToFavouritesFragment()
            view.findNavController().navigate(direction)
        }
    }

    private fun setupRecyclerView() {
        usersAdapter = GithubUsersPagingAdapter()

        binding.recyclerView
            .apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = usersAdapter
            }
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                usersAdapter.submitData(it)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}