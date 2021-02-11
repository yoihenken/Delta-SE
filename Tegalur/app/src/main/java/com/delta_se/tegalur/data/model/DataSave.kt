package com.delta_se.tegalur.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class DataSave(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,

    @ColumnInfo(name = "pageid")
    var pageid : String? = "",

    @ColumnInfo(name = "type")
    var type : String? = null
) : Parcelable
