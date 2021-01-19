package com.delta_se.tegalur.repository.network

import com.delta_se.tegalur.data.response.DetailResponse
import com.delta_se.tegalur.data.response.ListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DestinationContract {

    @GET("berita/{page}")
    fun getBeritaPage(
        @Path("page")
        page: Int?
    ): Call<ListResponse>

    @GET("berita/detail/{page}/{id}")
    fun getBeritaDetail(
        @Path("page")
        page: Int?,
        @Path("id")
        id: Int?
    ): Call<DetailResponse>

    @GET("pariwisata")
    fun getPariwisata(): Call<ListResponse>

    @GET("pariwisata/{id}")
    fun getPariwisataDetail(
        @Path("id")
        id: Int?
    ): Call<DetailResponse>

    @GET("oleh")
    fun getOleh(): Call<ListResponse>

    @GET("oleh/{id}")
    fun getOlehDetail(
        @Path("id")
        id: Int?
    ): Call<DetailResponse>

    @GET("event/{page}")
    fun getEvent(
        @Path("page")
        page: Int?
    ): Call<ListResponse>

    @GET("event/detail/{page}/{id}")
    fun getEventDetail(
        @Path("page")
        page: Int?,
        @Path("id")
        id: Int?
    ): Call<DetailResponse>

    @GET("kuliner")
    fun getKuliner(): Call<ListResponse>

    @GET("kuliner/{id}")
    fun getKulinerDetail(
        @Path("id")
        id: Int?
    ): Call<DetailResponse>

    @GET("penginapan")
    fun getPenginapan(): Call<ListResponse>

    @GET("penginapan/{id}")
    fun getPenginapanDetail(
        @Path("id")
        id: Int?
    ): Call<DetailResponse>
}