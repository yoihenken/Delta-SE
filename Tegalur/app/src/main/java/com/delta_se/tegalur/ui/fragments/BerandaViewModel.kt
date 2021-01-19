package com.delta_se.tegalur.ui.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.repository.network.DestinationServices
import kotlinx.coroutines.launch

class BerandaViewModel : ViewModel() {
    private val _berita = MutableLiveData<List<ListItem>>()
    val berita: LiveData<List<ListItem>> get() = _berita

    private val currentBerita = mutableListOf<ListItem>()

    fun getBerita(page: Int) = viewModelScope.launch {
        DestinationServices.getBeritaPage(page) {
            currentBerita.addAll(it.list ?: listOf())
            Log.d("BerandaViewModel", "getBerita: $it")
            Log.d("BerandaViewModel", "size: ${currentBerita.size}")
            _berita.value = currentBerita
        }
    }

    fun getCurrentNews(): List<ListItem> = currentBerita
}