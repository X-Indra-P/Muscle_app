package com.saxony.muscleapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Muscle(
    val name: String,
    val description: String,
    val photo: Int
) :Parcelable
