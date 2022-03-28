package com.example.bestrickandmorty.presentation.ui.fragments.characters.dialog

import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.example.bestrickandmorty.databinding.CharacterDialogBinding
import com.example.bestrickandmorty.domain.common.base.BaseDialogFragment
import com.example.bestrickandmorty.domain.common.extension.showToast

class CharacterDialog : BaseDialogFragment<CharacterDialogBinding>() {


    override fun setupData() {

    }

    override fun setupView() {

        binding.btnYes.setOnClickListener {
            val genderId = binding.rgGenderGroup.checkedRadioButtonId
            val gender = binding.root.findViewById<RadioButton>(genderId)
            val statusId = binding.rgStatusGroup.checkedRadioButtonId
            val status = binding.root.findViewById<RadioButton>(statusId)
            if (status != null && gender != null) {
                findNavController().navigate(
                    CharacterDialogDirections
                        .actionCharacterDialogToCharacterFragment(
                            status.text.toString(),
                            gender.text.toString()
                        )
                )
            } else {
                requireContext().showToast("Choose anything")
            }
        }
        binding.btnNo.setOnClickListener {
            dismiss()
        }
        binding.rgGenderGroup.clearCheck()
        binding.rgStatusGroup.clearCheck()
    }

    override fun bind(): CharacterDialogBinding {
        return CharacterDialogBinding.inflate(layoutInflater)
    }

}