package com.delta_se.tegalur.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class DataSave(
    @ColumnInfo(name = "page")
    var page : Int? = 0,
    @ColumnInfo(name = "id")
    var id : Int? = 0,
    @ColumnInfo(name = "type")
    var type : String? = null
) : Parcelable
