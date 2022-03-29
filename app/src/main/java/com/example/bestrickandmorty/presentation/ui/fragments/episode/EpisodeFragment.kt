package com.example.bestrickandmorty.presentation.ui.fragments.episode

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestrickandmorty.R
import com.example.bestrickandmorty.databinding.FragmentEpisodeBinding
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.searchText
import com.example.bestrickandmorty.domain.common.extension.stateLoad
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>() {

    private val viewModel: EpisodeViewModel by viewModels()
    private val adapter: EpisodeAdapter by lazy {
        EpisodeAdapter()
    }
    private val args: EpisodeFragmentArgs by navArgs()

    override fun setupData() {
        if (args.season != null) {
            viewModel.fetchEpisode("", args.season)
        }
    }

    override fun setupObservers() {
        observeEpisode()
    }

    private fun observeEpisode() {
        lifecycleScope.launchWhenCreated {
            viewModel.episodeList.collectLatest {
                it?.let { it1 -> adapter.submitData(it1) }
            }
        }
    }

    override fun setupUI() {
        binding.rvEpisode.apply {
            adapter = this@EpisodeFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
        adapter.stateLoad(binding.rvEpisode, binding.progress)
    }

    override fun setupListeners() {
        searchEpisode()
        adapter.onClickItem = {
            findNavController().navigate(
                EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(
                    it.id
                )
            )
        }
        binding.ivFilter.setOnClickListener {
            findNavController().navigate(R.id.action_episodeFragment_to_episodeDialog)
        }
    }

    private fun searchEpisode() {
        if (args.season == null) {
            binding.etEpisode.searchText {
                viewModel.fetchEpisode(it)
            }
        } else {
            binding.etEpisode.searchText {
                viewModel.fetchEpisode(it, args.season)
            }
        }
    }

    override fun bind(): FragmentEpisodeBinding {
        return FragmentEpisodeBinding.inflate(layoutInflater)
    }

}