package com.delta_se.tegalur.ui.fragments

import android.app.Activity
import android.app.Application
import androidx.lifecycle.*
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {
    private val _event = MutableLiveData<List<ListItem>>()
    val event : LiveData<List<ListItem>> get() = _event

    private val currentEvent = mutableListOf<ListItem>()
    private var pageIndex = 1

    fun getEvent(page : Int) = viewModelScope.launch {
        DestinationServices.getEvent(page) {
            if (page >= pageIndex){
                currentEvent.addAll(it.list ?: listOf())
                _event.value = currentEvent
                pageIndex++
            }
        }
    }

    private val _saved = MutableLiveData<List<DataSave>>()
    val saved : LiveData<List<DataSave>> get() = _saved

    fun getSavedEvent(application: Application, activity: Activity) = viewModelScope.launch {
        SimpanViewModel(application).getAllSimpan().collect {
            it.observe(activity as LifecycleOwner) { saved ->
                _saved.value = saved
            }
        }
    }
}