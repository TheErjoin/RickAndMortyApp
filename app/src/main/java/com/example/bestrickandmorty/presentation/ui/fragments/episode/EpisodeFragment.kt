package com.example.bestrickandmorty.presentation.ui.fragments.episode

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestrickandmorty.databinding.FragmentEpisodeBinding
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.stateLoad
import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>() {

    private val viewModel: EpisodeViewModel by viewModels()
    private val adapter: EpisodeAdapter by lazy {
        EpisodeAdapter()
    }

    override fun setupObservers() {
        observeEpisode()
    }

    private fun observeEpisode() {
        viewModel.fetchEpisode().flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleEpisode(it) }.launchIn(lifecycleScope)
    }

    private suspend fun handleEpisode(pagingData: PagingData<EpisodeEntity>) {
        adapter.submitData(pagingData)
    }

    override fun setupUI() {
        binding.rvEpisode.apply {
            adapter = this@EpisodeFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
        adapter.stateLoad(binding.rvEpisode, binding.progress)
    }

    override fun bind(): FragmentEpisodeBinding {
        return FragmentEpisodeBinding.inflate(layoutInflater)
    }

}