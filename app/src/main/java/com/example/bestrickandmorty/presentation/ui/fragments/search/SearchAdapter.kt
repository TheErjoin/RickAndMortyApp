package com.example.bestrickandmorty.presentation.ui.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bestrickandmorty.R
import com.example.bestrickandmorty.databinding.ItemCharacterBinding
import com.example.bestrickandmorty.databinding.ItemEpisodeBinding
import com.example.bestrickandmorty.databinding.ItemLocationBinding
import com.example.bestrickandmorty.domain.search.entity.FilterEntity
import com.example.bestrickandmorty.presentation.enums.StatusEnums

class SearchAdapter(
    private var list: MutableList<FilterEntity>,
    private var characterItemClick: (id: Int) -> Unit,
    private var locationItemClick: (id: Int) -> Unit,
    private var episodeItemClick: (id: Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            R.layout.item_character -> {
                return CharacterViewHolder(
                    characterItemClick,
                    ItemCharacterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            R.layout.item_episode -> {
                return EpisodeViewHolder(
                    locationItemClick,
                    ItemEpisodeBinding
                        .inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                )
            }
            R.layout.item_location -> {
                return LocationViewHolder(
                    episodeItemClick,
                    ItemLocationBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalAccessException("error")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> {
                holder.bind(list[position] as FilterEntity.CharactersEntity)
            }

            is EpisodeViewHolder -> {
                holder.onBind(list[position] as FilterEntity.EpisodesEntity)
            }

            is LocationViewHolder -> {
                holder.onBind(list[position] as FilterEntity.LocationsEntity)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is FilterEntity.CharactersEntity -> R.layout.item_character
            is FilterEntity.EpisodesEntity -> R.layout.item_episode
            is FilterEntity.LocationsEntity -> R.layout.item_location
        }
    }

    override fun getItemCount() = list.size

    class CharacterViewHolder(
        private val onItemClick: (id: Int) -> Unit,
        private val binding: ItemCharacterBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: FilterEntity.CharactersEntity) {
            binding.ivCharacter.load(result.characterEntity.image)
            binding.tvCharacterName.text = result.characterEntity.name
            binding.tvLastKnownLocation.text = result.characterEntity.location.name
            result.characterEntity.status.let { status(it) }
            itemView.setOnClickListener {
                 onItemClick(result.characterEntity.id)
            }
        }

        private fun status(status: String) {
            when (status) {
                StatusEnums.ALIVE.status -> {
                    binding.ivIndicator.setImageResource(StatusEnums.ALIVE.image)
                    binding.tvStatus.text = StatusEnums.ALIVE.status
                }
                StatusEnums.DEAD.status -> {
                    binding.ivIndicator.setImageResource(StatusEnums.DEAD.image)
                    binding.tvStatus.text = StatusEnums.DEAD.status
                }
                StatusEnums.UNKNOWN.status -> {
                    binding.ivIndicator.setImageResource(StatusEnums.UNKNOWN.image)
                    binding.tvStatus.text = StatusEnums.UNKNOWN.status
                }
            }
        }
    }

    class EpisodeViewHolder(
        private val onItemClick: (id: Int) -> Unit,
        private val binding: ItemEpisodeBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(episode: FilterEntity.EpisodesEntity) {
            binding.tvNameEpisode.text = episode.episodeEntity.name
            binding.tvCreatedAt.text = episode.episodeEntity.air_date
            itemView.setOnClickListener {
                onItemClick(episode.episodeEntity.id)
            }
        }
    }

    class LocationViewHolder(
        private val onItemClick: (id: Int) -> Unit,
        private val binding: ItemLocationBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(result: FilterEntity.LocationsEntity) {
            binding.tvLocationName.text = result.locationEntity.name
            binding.tvCreatedAt.text = result.locationEntity.created
            itemView.setOnClickListener {
                onItemClick(result.locationEntity.id)
            }
        }
    }
}
