package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataRecycler (
    var id : Int? = 0,
    var title : String?= null,
    var image : String?= null,
    var desc : String?= null,
    var isSaved : Boolean = false
) : Parcelable