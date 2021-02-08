package com.delta_se.tegalur.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delta_se.tegalur.data.response.DataDetail
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.launch

class DetailPenginapanViewModel : ViewModel(){
    private val _penginapan = MutableLiveData<DataDetail>()
    val penginapan : LiveData<DataDetail> get() = _penginapan

    fun getPenginapanDetail(index : Int) = viewModelScope.launch {
        DestinationServices.getPenginapanDetail(index){
            if (it.status == true) _penginapan.value = it.data
        }
    }
}