package com.example.bestrickandmorty.presentation.ui.fragments.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bestrickandmorty.databinding.ItemCharacterBinding
import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.common.base.BaseDiffCallBack
import com.example.bestrickandmorty.presentation.enums.StatusEnums

class CharacterAdapter :
    PagingDataAdapter<CharacterEntity, CharacterAdapter.CharacterViewHolder>(BaseDiffCallBack()) {

    var onClickItems: ((CharacterEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(character: CharacterEntity) {
            binding.ivCharacter.load(character.image)
            binding.tvCharacterName.text = character.name
            binding.tvLastKnownLocation.text = character.location.name
            status(character.status)
        }

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { it1 -> onClickItems?.invoke(it1) }
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
}