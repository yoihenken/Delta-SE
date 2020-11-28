package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataPariwisata (
    var title : String,
    var image : String,
    var date : String,
    var description : String,
    val lat: Double,
    val lang: Double,
    var isSaved : Boolean?
) : Parcelable