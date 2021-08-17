package com.hfad.wetherwithmvvm.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Weather (
    val city: City = City.getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
): Parcelable
