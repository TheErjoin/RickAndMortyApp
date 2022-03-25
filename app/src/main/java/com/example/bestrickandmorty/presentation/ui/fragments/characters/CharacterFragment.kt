package com.example.bestrickandmorty.presentation.ui.fragments.characters

import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestrickandmorty.databinding.FragmentCharacterBinding
import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import com.example.bestrickandmorty.domain.common.extension.stateLoad
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {

    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }
    private val viewModel: CharacterViewModel by viewModels()

    override fun setupObservers() {
        observeCharacter()
    }

    private fun searchCharacter() {
        binding.etCharacters.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.fetchCharacter(text.toString())
            }

        })
    }

    private fun observeCharacter() {
        viewModel.characterList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { it?.let { it1 -> handleCharacter(it1) } }.launchIn(lifecycleScope)
    }

    private suspend fun handleCharacter(pagingData: PagingData<CharacterEntity>) {
        adapter.submitData(pagingData)
    }

    override fun setupListeners() {
        adapter.onClickItems = {
            findNavController().navigate(
                CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
                    it.id
                )
            )
        }
//        searchCharacter()
    }


    override fun setupUI() {
        binding.rvCharacter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CharacterFragment.adapter
        }
        adapter.stateLoad(binding.rvCharacter, binding.progress)
    }

    override fun bind(): FragmentCharacterBinding {
        return FragmentCharacterBinding.inflate(layoutInflater)
    }
}