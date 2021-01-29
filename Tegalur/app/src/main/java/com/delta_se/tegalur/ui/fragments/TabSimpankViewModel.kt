package com.delta_se.tegalur.ui.fragments

import android.app.Activity
import android.app.Application
import androidx.lifecycle.*
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Observer

class TabSimpankViewModel : ViewModel() {

    private val _saved = MutableLiveData<List<DataSave>>()
    val saved : LiveData<List<DataSave>> get() = _saved

    fun getSavedData(application: Application, activity: Activity) = viewModelScope.launch {
        SimpanViewModel(application).getAllSimpan().collect {
            it.observe(activity as LifecycleOwner, { saved ->
                _saved.value = saved
            })
        }
    }

    private val _berita = MutableLiveData<List<ListItem>>()
    val beritaSimpan : LiveData<List<ListItem>> get() = _berita

    private val roomBerita = mutableListOf<ListItem>()

    fun getBeritaFromRoom(page: Int, id: Int) = viewModelScope.launch {
        roomBerita.removeAll(roomBerita)
        DestinationServices.getBeritaPage(page){
            it.list?.forEachIndexed { index, listItem ->
                if (index == id && !roomBerita.contains(listItem)) roomBerita.add(listItem)

            }
            _berita.value = roomBerita
        }
    }

    private val _event = MutableLiveData<List<ListItem>>()
    val eventSimpan : LiveData<List<ListItem>> get() = _event

    private val roomEvent = mutableListOf<ListItem>()

    fun getEventFromRoom(page: Int, id: Int) = viewModelScope.launch {
        roomEvent.removeAll(roomEvent)
        DestinationServices.getEvent(page){
            it.list?.forEachIndexed { index, listItem ->
                if (index == id && !roomEvent.contains(listItem)) roomEvent.add(listItem)
            }
            _event.value = roomEvent
        }
    }


}