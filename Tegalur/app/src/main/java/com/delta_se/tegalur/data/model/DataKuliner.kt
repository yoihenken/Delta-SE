package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataKuliner (
    var id : Int? = 0,
    var title : String? = null,
    var image : String? = null,
    var address : String? = null,
    var content : String? = null,
    var isSaved : Boolean = false
) : Parcelable
