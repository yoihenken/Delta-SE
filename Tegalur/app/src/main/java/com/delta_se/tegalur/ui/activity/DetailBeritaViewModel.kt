package com.delta_se.tegalur.ui.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delta_se.tegalur.data.response.ObjectDetail
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.launch

class DetailBeritaViewModel : ViewModel(){
    private val _berita = MutableLiveData<ObjectDetail>()
    val berita: LiveData<ObjectDetail> get() = _berita

    fun getBeritaDetail(page: Int, id: Int) = viewModelScope.launch {
        DestinationServices.getBeritaDetail(page, id){
            Log.d("DetailBeritaViewModel", "getBeritaDetail: $it")
            if (it.status == true) _berita.value = it.objectData
        }
    }

    private val _event = MutableLiveData<ObjectDetail>()
    val event: LiveData<ObjectDetail> get() = _event

    fun getEventDetail(page : Int, id: Int) = viewModelScope.launch {
        DestinationServices.getEventDetail(page,id){
            if (it.status == true) _event.value = it.objectData
        }
    }

}