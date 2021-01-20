package com.delta_se.tegalur.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {
    private val _event = MutableLiveData<List<ListItem>>()
    val event : LiveData<List<ListItem>> get() = _event

    private val currentEvent = mutableListOf<ListItem>()

    fun getEvent(page : Int) = viewModelScope.launch {
        DestinationServices.getEvent(page) {
            currentEvent.addAll(it.list ?: listOf())
            _event.value = currentEvent
        }
    }
}