package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataPariwisata (
    var title : String,
    var image : String,
    var address : String,
    var content : String,
    val lat: Double,
    val lang: Double,
    var isSaved : Boolean?
) : Parcelable