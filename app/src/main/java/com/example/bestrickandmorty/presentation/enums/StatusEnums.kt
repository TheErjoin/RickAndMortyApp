package com.example.bestrickandmorty.presentation.enums

import androidx.annotation.DrawableRes
import com.example.bestrickandmorty.R

enum class StatusEnums(
    val status: String,
    @DrawableRes val image: Int
) {

    ALIVE("Alive",R.drawable.alive_status),
    DEAD("Dead",R.drawable.dead_status),
    UNKNOWN("Unknown",R.drawable.unknown_status)

}