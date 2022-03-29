package com.example.bestrickandmorty.presentation.ui.fragments.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bestrickandmorty.databinding.ItemLocationBinding
import com.example.bestrickandmorty.domain.common.base.BaseDiffCallBack
import com.example.bestrickandmorty.domain.location.model.LocationEntity

class LocationAdapter :
    PagingDataAdapter<LocationEntity, LocationAdapter.LocationViewHolder>(BaseDiffCallBack()) {

    var onClickItem: ((LocationEntity) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationViewHolder {
        return LocationViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(location: LocationEntity) {
            binding.tvLocationName.text = location.name
            binding.tvCreatedAt.text = location.created

        }

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { it1 -> onClickItem?.invoke(it1) }
            }
        }
    }

}