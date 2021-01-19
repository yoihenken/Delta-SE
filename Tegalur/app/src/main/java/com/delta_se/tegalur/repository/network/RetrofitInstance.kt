package com.delta_se.tegalur.repository.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val baseSource : Retrofit = Retrofit.Builder()
        .baseUrl("http://tegal-city.kaedenoki.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getClient(): DestinationContract = baseSource.create(DestinationContract::class.java)
}