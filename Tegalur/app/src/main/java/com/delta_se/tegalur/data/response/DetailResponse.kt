package com.delta_se.tegalur.data.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

    //Api data manual
    @field:SerializedName("data")
    val data: DataDetail? = null,

    @field:SerializedName("status")
    val status: Boolean? = null,

    //Api scrapping
    @field:SerializedName("object")
    val objectData: ObjectDetail? = null
)

//Api data manual
data class DataDetail(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("website")
    val website: String? = null,

    @field:SerializedName("content")
    val content: String? = null
)

//Api scrapping
data class ObjectDetail(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("img")
    val img: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("tanggal")
    val tanggal: String? = null,

    @field:SerializedName("penulis")
    val penulis: String? = null,

    @field:SerializedName("isi")
    val isi: String? = null,

    @field:SerializedName("content")
    val content: String? = null,

    val page : Int? = 0,

    val id : Int? = 0
)
