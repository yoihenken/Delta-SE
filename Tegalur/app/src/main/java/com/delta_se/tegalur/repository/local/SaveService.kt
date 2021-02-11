package com.delta_se.tegalur.repository.local

import android.app.Application
import com.delta_se.tegalur.data.model.DataSave
import kotlinx.coroutines.flow.flow

class SaveService(application: Application) {

    private var saveDao: SaveDao
    private val database = SaveDatabase.getInstance(application)

    init {
        saveDao = database.saveDao()
    }

    suspend fun addToSave(dataSave : DataSave){
        saveDao.insert(dataSave)
    }

    suspend fun removeFromSave(dataSave: DataSave){
        saveDao.delete(dataSave)
    }

    suspend fun getAllSaved() = flow {
        emit(saveDao.getAllSave())
    }

    fun getAllData() = saveDao.getAllData()

}