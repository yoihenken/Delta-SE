package com.delta_se.tegalur.ui.fragments

import android.app.Activity
import android.app.Application
import android.util.Log
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
            it.observe(activity as LifecycleOwner) { saved ->
                _saved.value = saved
            }
        }
    }

    private val _berita = MutableLiveData<List<ListItem>>()
    val beritaSimpan : LiveData<List<ListItem>> get() = _berita

    private val roomBerita = mutableListOf<ListItem>()

    fun getBeritaFromRoom(page: Int, id: Int) = viewModelScope.launch {
        roomBerita.removeAll(roomBerita)
        DestinationServices.getBeritaPage(page){
            it.list?.forEachIndexed { index, listItem ->
                listItem.page = page
                listItem.id = id
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
                listItem.page = page
                listItem.id = id
                if (index == id && !roomEvent.contains(listItem)) roomEvent.add(listItem)
            }
            _event.value = roomEvent
        }
    }

    private val _pariwisata = MutableLiveData<List<ListItem>>()
    val pariwisataSimpan : LiveData<List<ListItem>> get() = _pariwisata

    private val roomPariwisata = mutableListOf<ListItem>()

    fun getPariwisataFromRoom(id: Int) = viewModelScope.launch {
        roomPariwisata.removeAll(roomPariwisata)
        DestinationServices.getPariwisata {
            it.data?.forEachIndexed { index, listItem ->
                listItem.id = id
                if (index == id && !roomPariwisata.contains(listItem)) roomPariwisata.add((listItem))
            }
            _pariwisata.value = roomPariwisata
        }
    }

    private val _penginapan = MutableLiveData<List<ListItem>>()
    val penginapanSimpan : LiveData<List<ListItem>> get() = _penginapan

    private val roomPenginapan = mutableListOf<ListItem>()

    fun getPenginapanFromRoom(id: Int) = viewModelScope.launch {
        roomPenginapan.removeAll(roomPenginapan)
        DestinationServices.getPenginapan {
            it.data?.forEachIndexed { index, listItem ->
                listItem.id = id
                if (index == id && !roomPenginapan.contains(listItem)) roomPenginapan.add((listItem))
            }
            _penginapan.value = roomPenginapan
        }
    }

}