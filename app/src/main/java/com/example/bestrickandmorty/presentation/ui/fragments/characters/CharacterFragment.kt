package com.example.bestrickandmorty.presentation.ui.fragments.characters

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestrickandmorty.R
import com.example.bestrickandmorty.databinding.FragmentCharacterBinding
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.searchText
import com.example.bestrickandmorty.domain.common.extension.showToast
import com.example.bestrickandmorty.domain.common.extension.stateLoad
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.NullPointerException

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {

    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }
    private val viewModel: CharacterViewModel by viewModels()
    private val args: CharacterFragmentArgs by navArgs()

    override fun setupData() {
        if (args.status == "" || args.gender == "") {
            viewModel.fetchCharacter()
        } else {
            viewModel.fetchCharacter("", args.status, args.gender)
        }
    }

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
        try {
            if (args.status == "" || args.gender == "") {
                binding.etCharacters.searchText {
                    viewModel.fetchCharacter(it)
                }
            } else {
                binding.etCharacters.searchText {
                    viewModel.fetchCharacter(it, args.status, args.gender)
                }
            }
        }catch (exception: NullPointerException){
            requireContext().showToast("Error")
        }

    }
    override fun setupUI() {
        binding.rvCharacter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CharacterFragment.adapter
        }
    }


    private fun observeCharacter() {
        lifecycleScope.launch {
            if (args.status == "" || args.gender == "") {
                viewModel.characterList.collectLatest {
                    it?.let { it1 -> adapter.submitData(it1) }
                }
            } else {
                viewModel.characterList.collectLatest {
                    it?.let { it1 -> adapter.submitData(it1) }
                }
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