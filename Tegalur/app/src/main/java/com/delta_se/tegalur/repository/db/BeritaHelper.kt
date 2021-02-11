package com.delta_se.tegalur.repository.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import com.delta_se.tegalur.repository.db.DatabaseContract.BeritaColumns.Companion.TABLE_BERITA
import com.delta_se.tegalur.repository.db.DatabaseContract.BeritaColumns.Companion._ID
import com.delta_se.tegalur.repository.db.DatabaseContract.BeritaColumns.Companion._PAGE

class BeritaHelper(context: Context) {
    companion object{
        private lateinit var databaseHelper: DatabaseHelper
        private val INSTANCE : BeritaHelper? = null
        private lateinit var database : SQLiteDatabase

        fun getInstance(context: Context) : BeritaHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: BeritaHelper(context)
            }
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLiteException::class)
    fun open(){
        database = databaseHelper.writableDatabase
    }

    fun close(){
        databaseHelper.close()
        if (database.isOpen) database.close()
    }

    fun queryAll() : Cursor{
        return database.query(
            TABLE_BERITA,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }

    fun queryByPageID(page : Int, id : Int) : Cursor {
        return database.query(
            TABLE_BERITA,
            null,
            "$_PAGE = ? && $_ID = ?",
            arrayOf(page.toString(), id.toString()),
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?) : Long {
        return database.insert(
            TABLE_BERITA, null, values
        )
    }

    fun update(page : String, id: String, values:ContentValues?) : Int {
        return database.update(
            TABLE_BERITA, values, "$_PAGE && $_ID", arrayOf(page, id)
        )
    }

    fun deleteByPageID(page : String, id : String) : Int{
        return database.delete(
            TABLE_BERITA, "$_PAGE = '$page' && $_ID = $'$id' ", null
        )
    }

}