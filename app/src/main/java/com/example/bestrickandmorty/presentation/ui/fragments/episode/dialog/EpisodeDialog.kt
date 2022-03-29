package com.example.bestrickandmorty.presentation.ui.fragments.episode.dialog

import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.example.bestrickandmorty.databinding.EpisodeDialogBinding
import com.example.bestrickandmorty.domain.common.base.BaseDialogFragment

class EpisodeDialog : BaseDialogFragment<EpisodeDialogBinding>() {

    override fun setupData() {
    }

    override fun setupView() {
        binding.btnApply.setOnClickListener {
            val seasonId = binding.rgSeasonGroup.checkedRadioButtonId
            val seasons = binding.root.findViewById<RadioButton>(seasonId)
            if (seasons != null) {
                findNavController().navigate(
                    EpisodeDialogDirections.actionEpisodeDialogToEpisodeFragment(
                        seasons.text.toString()
                    )
                )
            }
        }
    }

    override fun bind(): EpisodeDialogBinding {
        return EpisodeDialogBinding.inflate(layoutInflater)
    }

}