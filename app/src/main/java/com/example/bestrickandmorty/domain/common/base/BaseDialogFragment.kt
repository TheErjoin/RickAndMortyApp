package com.example.bestrickandmorty.domain.common.base

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {

    protected lateinit var binding: VB
    protected abstract fun bind(): VB

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = bind()
        val dialog = AlertDialog.Builder(activity).setView(binding.root).create()
        setupView()
        setupData()
        return dialog
    }

    abstract fun setupData()
    abstract fun setupView()

}