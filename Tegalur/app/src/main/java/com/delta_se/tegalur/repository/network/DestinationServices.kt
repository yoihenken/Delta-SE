package com.delta_se.tegalur.repository.network

import android.util.Log
import com.delta_se.tegalur.data.response.DetailResponse
import com.delta_se.tegalur.data.response.ListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationServices {
    fun getBeritaPage(page: Int?, callback: (ListResponse) -> Unit){
        RetrofitInstance.getClient().getBeritaPage(page ?: 1).enqueue(
            object : Callback<ListResponse>{
                override fun onResponse(
                    call: Call<ListResponse>,
                    response: Response<ListResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getBeritaDetail(page: Int?, id: Int?, callback: (DetailResponse) -> Unit){
        RetrofitInstance.getClient().getBeritaDetail(page ?: 1, id ?: 1).enqueue(
            object : Callback<DetailResponse>{
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getPariwisata(callback: (ListResponse) -> Unit){
        RetrofitInstance.getClient().getPariwisata().enqueue(
            object  : Callback<ListResponse>{
                override fun onResponse(
                    call: Call<ListResponse>,
                    response: Response<ListResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getPariwisataDetail(id: Int?, callback: (DetailResponse) -> Unit){
        RetrofitInstance.getClient().getPariwisataDetail(id ?: 1).enqueue(
            object : Callback<DetailResponse>{
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getOleh(callback: (ListResponse) -> Unit){
        RetrofitInstance.getClient().getOleh().enqueue(
            object :Callback<ListResponse>{
                override fun onResponse(
                    call: Call<ListResponse>,
                    response: Response<ListResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getOlehDetail(id: Int?, callback: (DetailResponse) -> Unit){
        RetrofitInstance.getClient().getOlehDetail(id ?: 1).enqueue(
            object : Callback<DetailResponse>{
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getEvent(page: Int?, callback: (ListResponse) -> Unit){
        RetrofitInstance.getClient().getEvent(page ?: 1).enqueue(
            object : Callback<ListResponse>{
                override fun onResponse(
                    call: Call<ListResponse>,
                    response: Response<ListResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getEventDetail(page: Int?, id: Int?, callback: (DetailResponse) -> Unit){
        RetrofitInstance.getClient().getEventDetail(page ?: 1, id ?: 1).enqueue(
            object : Callback<DetailResponse>{
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getKuliner(callback: (ListResponse) -> Unit){
        RetrofitInstance.getClient().getKuliner().enqueue(
            object : Callback<ListResponse>{
                override fun onResponse(
                    call: Call<ListResponse>,
                    response: Response<ListResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getKulinerDetail(id: Int?, callback: (DetailResponse) -> Unit){
        RetrofitInstance.getClient().getKulinerDetail(id ?: 1).enqueue(
            object : Callback<DetailResponse>{
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getPenginapan(callback: (ListResponse) -> Unit){
        RetrofitInstance.getClient().getPenginapan().enqueue(
            object : Callback<ListResponse>{
                override fun onResponse(
                    call: Call<ListResponse>,
                    response: Response<ListResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getPenginapanDetail(id: Int?, callback: (DetailResponse) -> Unit){
        RetrofitInstance.getClient().getPenginapanDetail(id ?: 1).enqueue(
            object : Callback<DetailResponse>{
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }
}