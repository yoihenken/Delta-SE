package com.delta_se.tegalur.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.repository.local.SaveService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SimpanViewModel(application: Application) : AndroidViewModel(application) {

    private val service = SaveService(application)

    suspend fun getAllSimpan() : Flow<LiveData<List<DataSave>>> {
        return service.getAllSaved()
    }

    suspend fun addToSave(dataSave: DataSave) = viewModelScope.launch{
        service.addToSave(dataSave)
    }

    suspend fun removeFromSave(dataSave: DataSave) = viewModelScope.launch{
        service.removeFromSave(dataSave)
    }

}