package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataBerita (
    var title : String? = null,
    var image : String? = null,
    var date : String? = null,
    var writer : String? = null,
    var description : String? = null,
    var isSaved : Boolean = false
) : Parcelable