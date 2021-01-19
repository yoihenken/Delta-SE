package com.delta_se.tegalur.utils

import com.delta_se.tegalur.data.model.DataBerita
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

}