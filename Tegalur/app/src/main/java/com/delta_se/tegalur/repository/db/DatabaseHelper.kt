package com.delta_se.tegalur.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.delta_se.tegalur.repository.db.DatabaseContract.BeritaColumns.Companion.TABLE_BERITA
import com.delta_se.tegalur.repository.db.DatabaseContract.EventColumns.Companion.TABLE_EVENT

internal class DatabaseHelper (context: Context) :  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "dbtegalurapp"
        private const val DATABASE_VERSION = 5
        private const val SQL_CREATE_TABLE_BERITA =
            "CREATE TABLE $TABLE_BERITA (" +
                    "${DatabaseContract.BeritaColumns._PAGE} INTEGER, " +
                    "${DatabaseContract.BeritaColumns._ID} INTEGER, " +
                    "${DatabaseContract.BeritaColumns.TITLE} TEXT, " +
                    "${DatabaseContract.BeritaColumns.IMAGE} TEXT, " +
                    "${DatabaseContract.BeritaColumns.DATE} TEXT, " +
                    "${DatabaseContract.BeritaColumns.WRITER} TEXT, " +
                    "${DatabaseContract.BeritaColumns.DESCRIPTION} TEXT, " +
                    "${DatabaseContract.BeritaColumns.ISSAVED} BOOLEAN) "

        private const val SQL_CREATE_TABLE_EVENT =
            "CREATE TABLE $TABLE_EVENT (" +
                    "${DatabaseContract.EventColumns._PAGE} INTEGER, " +
                    "${DatabaseContract.EventColumns._ID} INTEGER, " +
                    "${DatabaseContract.EventColumns.TITLE} TEXT, " +
                    "${DatabaseContract.EventColumns.DATE} TEXT, " +
                    "${DatabaseContract.EventColumns.IMAGE} TEXT, " +
                    "${DatabaseContract.EventColumns.CONTENT} TEXT, " +
                    "${DatabaseContract.EventColumns.ISSAVED} BOOLEAN) "
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null){
            db.execSQL(SQL_CREATE_TABLE_BERITA)
            db.execSQL(SQL_CREATE_TABLE_EVENT)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        if (db != null){
            db.execSQL("DROP TABLE IF EXISTS $TABLE_BERITA")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_EVENT")
        }
        onCreate(db)
    }


}