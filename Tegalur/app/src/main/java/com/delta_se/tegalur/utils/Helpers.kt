package com.delta_se.tegalur.utils

import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataEvent
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.data.response.ObjectDetail

object Helpers {
    fun List<ListItem>.toDataBerita(): MutableList<DataBerita> {
        val berita = mutableListOf<DataBerita>()
        this.forEach {
            berita.add(
                DataBerita(it.title, it.img, it.date, null, null, false)
            )
        }
        return berita
    }

    fun ObjectDetail.toDataBerita(): MutableList<DataBerita>{
        val berita = mutableListOf<DataBerita>()
        berita.add(
            DataBerita(title, img, tanggal, penulis, isi, false)
        )
        return berita
    }

    fun List<ListItem>.toDataEvent(): MutableList<DataEvent>{
        val event = mutableListOf<DataEvent>()
        this.forEach {
            event.add(DataEvent(0, 0,  it.title, it.date, it.image, null, false))
        }
        return event
    }

    fun ObjectDetail.toDataEvent(): MutableList<DataEvent>{
        val event = mutableListOf<DataEvent>()
        event.add(DataEvent(0, 0, title, image, null, content, false))
        return event
    }

}