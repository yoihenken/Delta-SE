package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataMap(
    var title : String ?= null,
    var adress : String ?= null,
    var lat : Double ?= null,
    var lang : Double ?= null
) : Parcelable