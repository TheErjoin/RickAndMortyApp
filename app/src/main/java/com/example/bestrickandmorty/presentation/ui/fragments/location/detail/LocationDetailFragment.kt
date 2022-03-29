package com.example.bestrickandmorty.presentation.ui.fragments.location.detail

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.bestrickandmorty.databinding.FragmentLocationDetailBinding
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.showToast
import com.example.bestrickandmorty.domain.location.model.LocationEntity
import com.example.bestrickandmorty.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LocationDetailFragment : BaseFragment<FragmentLocationDetailBinding>() {

    private val viewModel: LocationDetailViewModel by viewModels()
    private val args: LocationDetailFragmentArgs by navArgs()

    override fun setupObservers() {
        viewModel.stateLocationDetail.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleData(it) }.launchIn(lifecycleScope)
    }

    private fun handleData(it: UiState<LocationEntity>) {
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
                    tvDimension.text = it.data.dimension
                    tvType.text = it.data.type
                    tvCreated.text = it.data.created
                    tvName.text = it.data.name
                }
                isViewVisible(true)
            }
        }
    }

    private fun isViewVisible(boolean: Boolean) {
        binding.tvNameUp.isVisible = boolean
        binding.tvDataCreatedUp.isVisible = boolean
        binding.tvDimensionUp.isVisible = boolean
        binding.tvTypeUp.isVisible = boolean
    }

    override fun setupUI() {
        viewModel.getDataLocationDetail(args.id)
    }

    override fun bind(): FragmentLocationDetailBinding {
        return FragmentLocationDetailBinding.inflate(layoutInflater)
    }
}
