package com.delta_se.tegalur.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delta_se.tegalur.data.response.DataDetail
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.launch

class DetailOlehViewModel : ViewModel() {
    private val _oleh = MutableLiveData<DataDetail>()
    val oleh : LiveData<DataDetail> get() = _oleh

    fun getOlehDetail(index : Int) = viewModelScope.launch {
        DestinationServices.getOlehDetail(index){
            if (it.status == true) _oleh.value = it.data
        }
    }
}