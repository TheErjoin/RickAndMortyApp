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

    var onClickItem: ((EpisodeEntity) -> Unit)? = null

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

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(episode: EpisodeEntity) {
            binding.tvNameEpisode.text = episode.name
            binding.tvCreatedAt.text = episode.air_date
        }

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { it1 -> onClickItem?.invoke(it1) }
            }
        }

    }
}