package com.example.bestrickandmorty.domain.common.extension

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView

fun <T : Any, VH : RecyclerView.ViewHolder> PagingDataAdapter<T, VH>.stateLoad(
    recyclerView: RecyclerView,
    progressBar: ProgressBar,
) {
    addLoadStateListener { loadStates ->
        recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
        progressBar.isVisible = loadStates.refresh is LoadState.Loading
    }
}