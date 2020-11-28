package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataRecycler (
    var title : String,
    var image : String,
    var desc : String,
    var isSaved : Boolean?
) : Parcelable