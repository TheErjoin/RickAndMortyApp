package com.example.bestrickandmorty.presentation.ui.fragments.search

import com.example.bestrickandmorty.databinding.FragmentSearchBinding
import com.example.bestrickandmorty.domain.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun setupObservers() {

    }

    override fun setupUI() {

    }

    override fun bind(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

}