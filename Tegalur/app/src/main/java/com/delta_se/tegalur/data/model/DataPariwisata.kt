package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataPariwisata (
    var title : String,
    var image : String,
    var address : String,
    var content : String,
    var lat: Double,
    var lang: Double,
    var isSaved : Boolean?
) : Parcelable