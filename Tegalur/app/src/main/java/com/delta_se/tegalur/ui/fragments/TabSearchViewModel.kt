package com.delta_se.tegalur.ui.fragments

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.data.response.DataDetail
import com.delta_se.tegalur.data.response.DetailResponse
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TabSearchViewModel : ViewModel(){
    private val _pariwisata = MutableLiveData<List<ListItem>>()
    val pariwisata : LiveData<List<ListItem>> get() = _pariwisata

    fun getPariwisata() = viewModelScope.launch {

        DestinationServices.getPariwisata {
            _pariwisata.value = it.data
//            Log.d("TabSearchViewModel", " ${it.data} ")
        }
    }

    private val _savedPariw = MutableLiveData<List<DataSave>>()
    val savedPariw : LiveData<List<DataSave>> get() = _savedPariw

    val roomSave = mutableListOf<DataSave>()

    fun getSavedTourism(application: Application, activity: Activity) = viewModelScope.launch {
        roomSave.removeAll(roomSave)
        SimpanViewModel(application).getAllSimpan().collect {
            it.observe(activity as LifecycleOwner) {room ->
                room.forEach { item ->
                    if (item.type.equals("PARIWISATA")) roomSave.add(item)
                }
                _savedPariw.value = roomSave.distinct().toMutableList()
                Log.d("TabSearchViewModel", " saved : ${_savedPariw.value} ")
                Log.d("TabSearchViewModel", " roomAfter : $roomSave ")
            }
        }
    }

    private val _kuliner = MutableLiveData<List<ListItem>>()
    val kuliner : LiveData<List<ListItem>> get() = _kuliner

    fun getKuliner() = viewModelScope.launch {
        DestinationServices.getKuliner {
            _kuliner.value = it.data
        }
    }

    private val _savedKul = MutableLiveData<List<DataSave>>()
    val savedKul : LiveData<List<DataSave>> get() = _savedKul

    fun getSavedKul(application: Application, activity: Activity) = viewModelScope.launch {
        roomSave.removeAll(roomSave)
        SimpanViewModel(application).getAllSimpan().collect {
            it.observe(activity as LifecycleOwner) { room ->
                room.forEach { item ->
                    if (item.type.equals("KULINER")) roomSave.add(item)
                }
                _savedKul.value = roomSave.distinct().toMutableList()
            }
        }
    }

    private val _oleh = MutableLiveData<List<ListItem>>()
    val oleh : LiveData<List<ListItem>> get() = _oleh

    fun getOleh() = viewModelScope.launch {
        DestinationServices.getOleh {
            _oleh.value = it.data
        }
    }

    private val _savedOleh = MutableLiveData<List<DataSave>>()
    val savedOleh : LiveData<List<DataSave>> get() = _savedOleh

    fun getSavedOleh(application: Application, activity: Activity) = viewModelScope.launch {
        roomSave.removeAll(roomSave)
        SimpanViewModel(application).getAllSimpan().collect {
            it.observe(activity as LifecycleOwner) { room ->
                room.forEach { item ->
                    if (item.type.equals("OLEH")) roomSave.add(item)
                }
                _savedOleh.value = roomSave.distinct().toMutableList()
            }
        }
    }

    private val _penginapan = MutableLiveData<List<ListItem>>()
    val penginapan : LiveData<List<ListItem>> get() = _penginapan

    fun getPenginapan() = viewModelScope.launch {
        DestinationServices.getPenginapan {
            _penginapan.value = it.data
        }
    }

    private val _savedPeng = MutableLiveData<List<DataSave>>()
    val savedPeng : LiveData<List<DataSave>> get() = _savedPeng

    fun getSavedLodging(application: Application, activity: Activity) = viewModelScope.launch {
        roomSave.removeAll(roomSave)
        SimpanViewModel(application).getAllSimpan().collect {
            it.observe(activity as LifecycleOwner) { room ->
                room.forEach { item ->
                    if (item.type.equals("PENGINAPAN")) roomSave.add(item)
                }
                _savedPeng.value = roomSave.distinct().toMutableList()
            }
        }
    }

}