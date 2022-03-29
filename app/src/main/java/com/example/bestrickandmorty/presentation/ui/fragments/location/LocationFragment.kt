package com.example.bestrickandmorty.presentation.ui.fragments.location

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestrickandmorty.databinding.FragmentLocationBinding
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.searchText
import com.example.bestrickandmorty.domain.common.extension.stateLoad
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding>() {

    private val viewModel: LocationViewModel by viewModels()
    private val adapter: LocationAdapter by lazy {
        LocationAdapter()
    }

    override fun setupObservers() {
        observeLocation()
    }

    private fun observeLocation() {
        lifecycleScope.launchWhenCreated {
            viewModel.locationList.collectLatest {
                it?.let { it1 -> adapter.submitData(it1) }
            }
        }
    }

    override fun setupUI() {
        binding.rvLocation.apply {
            adapter = this@LocationFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
        adapter.stateLoad(binding.rvLocation, binding.progress)
    }

    override fun setupListeners() {
        binding.etCharacters.searchText {
            viewModel.fetchLocation(it)
        }
        adapter.onClickItem = {
            findNavController().navigate(
                LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(
                    it.id
                )
            )
        }
    }

    override fun bind(): FragmentLocationBinding {
        return FragmentLocationBinding.inflate(layoutInflater)
    }
}