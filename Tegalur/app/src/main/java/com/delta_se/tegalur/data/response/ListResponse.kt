package com.delta_se.tegalur.data.response

import com.google.gson.annotations.SerializedName

data class ListResponse(

    @field:SerializedName("list")
    val list: List<ListItem>? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class ListItem(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("img")
    val img: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("date")
    val date: String? = null,

    var page : Int? = 0,

    var id : Int? = 0

)