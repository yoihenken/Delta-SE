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

    private val _saved = MutableLiveData<List<DataSave>>()
    val saved : LiveData<List<DataSave>> get() = _saved

    fun getSavedNews(application: Application, activity: Activity) = viewModelScope.launch {
        SimpanViewModel(application).getAllSimpan().collect {
            it.observe(activity as LifecycleOwner, { saved ->
                _saved.value = saved
            })
        }
    }

}