package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataPariwisata (
    val id : Int? = null,
    var title : String? = null,
    var image : String? = null,
    var address : String? = null,
    var content : String? = null,
    var lat: Double? = null,
    var lang: Double? = null,
    var isSaved : Boolean = false
) : Parcelable