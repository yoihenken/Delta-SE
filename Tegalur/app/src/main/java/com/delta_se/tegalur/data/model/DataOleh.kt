package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataOleh (
    val id : Int? = 0,
    val title : String? = null,
    val image : String? = null,
    val content : String? = null
) : Parcelable