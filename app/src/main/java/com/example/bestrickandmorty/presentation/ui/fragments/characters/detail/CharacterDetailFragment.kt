package com.example.bestrickandmorty.presentation.ui.fragments.characters.detail

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.example.bestrickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.showToast
import com.example.bestrickandmorty.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding>() {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun setupObservers() {
        viewModel.stateCharacterDetailId.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleDetail(it) }.launchIn(lifecycleScope)
    }

    private fun handleDetail(it: UiState<CharacterEntity>) {
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
                    ivImageCharacter.load(it.data.image) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                    tvLocation.text = it.data.location.name
                    tvGender.text = it.data.gender
                    tvType.text = it.data.species
                    tvStatus.text = it.data.status
                    tvName.text = it.data.name
                }
                isViewVisible(true)
            }
        }
    }


    override fun setupUI() {
        viewModel.getDataCharacterDetail(args.id)
    }

    override fun bind(): FragmentCharacterDetailBinding {
        return FragmentCharacterDetailBinding.inflate(layoutInflater)
    }

    private fun isViewVisible(boolean: Boolean) {
        binding.tvNameUp.isVisible = boolean
        binding.tvGenderUp.isVisible = boolean
        binding.tvLocationUp.isVisible = boolean
        binding.tvSpeciesUp.isVisible = boolean
        binding.tvStatusUp.isVisible = boolean
    }
}