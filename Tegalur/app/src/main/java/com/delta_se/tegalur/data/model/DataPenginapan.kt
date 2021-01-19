package com.delta_se.tegalur.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataPenginapan(
    var title : String,
    var image : String,
    var address : String,
    var phone : Int,
    var price : Double,
    var website : String,
    var content : String,
    var isSaved : Boolean?
) : Parcelable
