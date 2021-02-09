package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataOleh (
    var id : Int? = null,
    var title : String? = null,
    var image : String? = null,
    var content : String? = null,
    var isSaved : Boolean = false

) : Parcelable