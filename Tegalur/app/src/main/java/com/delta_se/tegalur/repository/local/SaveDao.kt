package com.delta_se.tegalur.repository.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.delta_se.tegalur.data.model.DataSave

@Dao
interface SaveDao {

    @Insert
    suspend fun insert(dataSave : DataSave)

    @Delete
    suspend fun delete(dataSave : DataSave)

    @Query("select * from DataSave")
    fun getAllSave() : LiveData<List<DataSave>>

    @Query("select * from DataSave")
    fun getAllSaveMain() : List<DataSave>

    @Query("select * from DataSave")
    fun getAllData() : Cursor
}