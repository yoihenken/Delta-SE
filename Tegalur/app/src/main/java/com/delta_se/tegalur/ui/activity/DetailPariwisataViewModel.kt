package com.delta_se.tegalur.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delta_se.tegalur.data.response.DataDetail
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.launch

class DetailPariwisataViewModel : ViewModel() {
    private val _pariwisata = MutableLiveData<DataDetail>()
    val pariwisata : LiveData<DataDetail> get() = _pariwisata

    fun getPariwisataDetail(index : Int) = viewModelScope.launch {
        DestinationServices.getPariwisataDetail(index){
            if (it.status == true) _pariwisata.value = it.data
        }
    }
}