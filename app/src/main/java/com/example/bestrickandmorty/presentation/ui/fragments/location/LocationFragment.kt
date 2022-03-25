package com.example.bestrickandmorty.presentation.ui.fragments.location

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestrickandmorty.databinding.FragmentLocationBinding
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.stateLoad
import com.example.bestrickandmorty.domain.location.model.LocationEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
        viewModel.fetchLocation().flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleLocation(it) }.launchIn(lifecycleScope)
    }

    private suspend fun handleLocation(it: PagingData<LocationEntity>) {
        adapter.submitData(it)
    }

    override fun setupUI() {
        binding.rvLocation.apply {
            adapter = this@LocationFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
        adapter.stateLoad(binding.rvLocation, binding.progress)
    }

    override fun bind(): FragmentLocationBinding {
        return FragmentLocationBinding.inflate(layoutInflater)
    }
}