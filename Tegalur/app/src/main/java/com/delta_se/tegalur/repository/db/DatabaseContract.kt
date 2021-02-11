package com.delta_se.tegalur.repository.db

import android.provider.BaseColumns

internal class DatabaseContract {
    internal class BeritaColumns : BaseColumns {
        companion object {
            const val TABLE_BERITA = "berita"
            const val _PAGE = "_page"
            const val _ID = "_id"
            const val TITLE = "title"
            const val IMAGE = "image"
            const val DATE = "date"
            const val WRITER = "writer"
            const val DESCRIPTION = "description"
            const val ISSAVED = "issaved"
        }
    }

    internal class EventColumns : BaseColumns {
        companion object {
            const val TABLE_EVENT = "event"
            const val _PAGE = "_page"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DATE = "date"
            const val IMAGE = "image"
            const val CONTENT = "content"
            const val ISSAVED ="issaved"
        }
    }

}