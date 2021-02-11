package com.delta_se.tegalur.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delta_se.tegalur.data.response.DataDetail
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.launch

class DetailKulinerViewModel : ViewModel(){
    private val _kuliner = MutableLiveData<DataDetail>()
    val kuliner : LiveData<DataDetail> get() = _kuliner

    fun getKulinerDetail(index : Int) = viewModelScope.launch {
        DestinationServices.getKulinerDetail(index){
            if (it.status == true) _kuliner.value = it.data
        }
    }
}