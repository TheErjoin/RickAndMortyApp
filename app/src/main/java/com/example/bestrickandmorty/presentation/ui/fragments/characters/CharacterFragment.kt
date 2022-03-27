package com.example.bestrickandmorty.presentation.ui.fragments.characters

import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestrickandmorty.R
import com.example.bestrickandmorty.databinding.FragmentCharacterBinding
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.showToast
import com.example.bestrickandmorty.domain.common.extension.stateLoad
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {

    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }
    private val viewModel: CharacterViewModel by viewModels()

    override fun setupObservers() {
        observeState()
        observeCharacter()
    }

    private fun observeState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collectLatest {
                handleState(it)
            }
        }
    }

    private fun handleState(state: CharacterViewModel.CharacterFragmentState) {
        when (state) {
            is CharacterViewModel.CharacterFragmentState.IsLoading -> adapter.stateLoad(
                binding.rvCharacter,
                binding.progress
            )
            is CharacterViewModel.CharacterFragmentState.ShowToast -> {
                state.message?.let { requireContext().showToast(it) }
            }
            is CharacterViewModel.CharacterFragmentState.Init -> Unit
        }
    }

    private fun searchCharacter() {
        binding.etCharacters.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (viewModel.characterList.value != null) {
                    viewModel.fetchCharacter(p0)
                }
                return false
            }
        })
    }


    override fun setupUI() {
        binding.rvCharacter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CharacterFragment.adapter
        }
    }


    private fun observeCharacter() {
        lifecycleScope.launch {
            viewModel.characterList.collectLatest {
                it?.let { it1 -> adapter.submitData(it1) }
            }
        }
    }

    override fun setupListeners() {
        searchCharacter()
        adapter.onClickItems = {
            findNavController().navigate(
                CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
                    it.id
                )
            )
        }
        binding.ivFilter.setOnClickListener {
            findNavController().navigate(R.id.action_characterFragment_to_characterDialog)
        }

    }
    override fun bind(): FragmentCharacterBinding {
        return FragmentCharacterBinding.inflate(layoutInflater)
    }
}