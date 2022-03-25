package com.example.bestrickandmorty.presentation.ui.fragments.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bestrickandmorty.databinding.ItemEpisodeBinding
import com.example.bestrickandmorty.domain.common.base.BaseDiffCallBack
import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity

class EpisodeAdapter :
    PagingDataAdapter<EpisodeEntity, EpisodeAdapter.EpisodeViewHolder>(BaseDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(episode: EpisodeEntity) {
            binding.tvNameEpisode.text = episode.name
            binding.tvCreatedAt.text = episode.air_date
        }

    }
}