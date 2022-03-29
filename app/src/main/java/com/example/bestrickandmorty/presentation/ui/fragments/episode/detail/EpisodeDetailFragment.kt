package com.example.bestrickandmorty.presentation.ui.fragments.episode.detail

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.bestrickandmorty.databinding.FragmentEpisodeDetailBinding
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.showToast
import com.example.bestrickandmorty.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class EpisodeDetailFragment : BaseFragment<FragmentEpisodeDetailBinding>() {

    private val viewModel: EpisodeDetailViewModel by viewModels()
    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun setupObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.stateEpisodeDetail.collectLatest {
                binding.progress.isVisible = it is UiState.Loading

                when (it) {
                    is UiState.Loading -> {
                        isViewVisible(false)
                    }
                    is UiState.Error -> {
                        requireContext().showToast("Oops!Error:D")
                        isViewVisible(false)
                    }
                    is UiState.Success -> {
                        with(binding) {
                            tvCreated.text = it.data.created
                            tvEpisode.text = it.data.episode
                            tvName.text = it.data.name
                        }
                        isViewVisible(true)
                    }
                }
            }
        }
    }

    private fun isViewVisible(b: Boolean) {
        binding.tvCreatedUp.isVisible = b
        binding.tvEpisodeUp.isVisible = b
        binding.tvNameUp.isVisible = b
    }

    override fun setupUI() {
        viewModel.getDataEpisodeDetail(args.id)
    }

    override fun bind(): FragmentEpisodeDetailBinding {
        return FragmentEpisodeDetailBinding.inflate(layoutInflater)
    }

}