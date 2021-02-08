package com.delta_se.tegalur.ui.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delta_se.tegalur.data.response.DataDetail
import com.delta_se.tegalur.data.response.DetailResponse
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.launch

class TabSearchViewModel : ViewModel(){
    private val _pariwisata = MutableLiveData<List<ListItem>>()
    val pariwisata : LiveData<List<ListItem>> get() = _pariwisata

    fun getPariwisata() = viewModelScope.launch {
        DestinationServices.getPariwisata {
            _pariwisata.value = it.data
            Log.d("TabSearchViewModel", " ${it.data} ")
        }
    }
}