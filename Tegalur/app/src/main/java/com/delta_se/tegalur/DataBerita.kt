package com.delta_se.tegalur

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataBerita (
    var title : String,
    var image : String,
    var date : String,
    var isSaved : Boolean
) : Parcelable