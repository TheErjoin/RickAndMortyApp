package com.example.bestrickandmorty.presentation.ui.fragments.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestrickandmorty.databinding.FragmentSearchBinding
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.searchText
import com.example.bestrickandmorty.domain.search.entity.FilterEntity
import com.example.bestrickandmorty.domain.search.entity.toCharacterEntity
import com.example.bestrickandmorty.domain.search.entity.toEpisodeEntity
import com.example.bestrickandmorty.domain.search.entity.toLocationEntity
import com.example.bestrickandmorty.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchVIewModel by viewModels()
    private val list = mutableListOf<FilterEntity>()
    private val adapter: SearchAdapter by lazy {
        SearchAdapter(
            list,
            this::onItemCharacter,
            this::onItemLocation,
            this::onItemEpisode
        )
    }

    override fun setupData() {
        searchItem("")
    }

    private fun searchItem(s: String) {
        viewModel.fetchLocations(s)
        viewModel.fetchEpisodes(s)
        viewModel.fetchCharacter(s)
    }

    override fun setupObservers() {
        observeCharacter()
        observeLocation()
        observeEpisode()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeEpisode() {
        lifecycleScope.launchWhenCreated {
            viewModel.fetchCharacterSearch.collectLatest {
                binding.progress.isVisible = it is UiState.Loading
                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Error -> {
                        Log.e("TAG", "observeEpisode: $it")
                    }
                    is UiState.Success -> {
                        it.data.map {
                            it.toCharacterEntity()
                        }.let {
                            val sort = it.sortedByDescending { it1 ->
                                it1.characterEntity.created
                            }
                            list.addAll(sort)
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun observeLocation() {
        lifecycleScope.launchWhenCreated {
            viewModel.fetchLocationSearch.collectLatest {
                binding.progress.isVisible = it is UiState.Loading
                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Error -> {
                        Log.e("TAG", "observe: $it")
                    }
                    is UiState.Success -> {
                        it.data.map {
                            it.toLocationEntity()
                        }.let {
                            val sort = it.sortedByDescending { it1 ->
                                it1.locationEntity.created
                            }
                            list.addAll(sort)
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    private fun observeCharacter() {
        lifecycleScope.launchWhenCreated {
            viewModel.fetchEpisodeSearch.collectLatest {
                binding.progress.isVisible = it is UiState.Loading

                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Error -> {
                        Log.e("TAG", "observe: $it")
                    }
                    is UiState.Success -> it.data.map {
                        it.toEpisodeEntity()
                    }.let {
                        val sort = it.sortedByDescending { it1 ->
                            it1.episodeEntity.created
                        }
                        list.addAll(sort)
                    }
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun setupListeners() {
        binding.etSearch.searchText {
            it?.let { it1 -> searchItem(it1) }
            list.clear()
        }
    }

    override fun setupUI() {
        binding.rvSearch.apply {
            adapter = this@SearchFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun onItemEpisode(id: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToLocationDetailFragment(
                id
            )
        )
    }

    private fun onItemLocation(id: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToEpisodeDetailFragment(
                id
            )
        )
    }

    private fun onItemCharacter(id: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToCharacterDetailFragment(
                id
            )
        )
    }

    override fun bind(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

}