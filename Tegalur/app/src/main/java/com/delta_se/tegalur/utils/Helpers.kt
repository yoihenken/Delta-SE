package com.delta_se.tegalur.utils

import android.util.Log
import com.delta_se.tegalur.data.model.*
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.data.response.ObjectDetail
import java.text.SimpleDateFormat

object Helpers {

    //    RETROFIT
    fun List<ListItem>.toDataBerita(): MutableList<Any> {
        val berita = mutableListOf<Any>()
        var currentDate = ""
        var position = 0
        this.forEach {
            if (!currentDate.equals(getMonthYearfromDMYT(it.date!!))) {
                currentDate = getMonthYearfromDMYT(it.date)
                berita.add(currentDate)
            }
            berita.add(
                DataBerita(
                    getPage(position, 15),
                    getId(position, 15),
                    it.title,
                    it.img,
                    it.date,
                    null,
                    null,
                    false
                )
            )
            position++
        }
        return berita
    }

    //    Change List to Berita Room
    fun List<ListItem>.toDataBeritaFromRoom(): MutableList<Any> {
        val berita = mutableListOf<Any>()
        this.forEach {
            berita.add(
                DataBerita(it.page, it.id, it.title, it.img, it.date, null, null, true)
            )
        }
        return berita
    }

    fun ObjectDetail.toDataBerita(): MutableList<DataBerita> {
        val berita = mutableListOf<DataBerita>()
        berita.add(
            DataBerita(0, 0, title, img, tanggal, penulis, isi, false)
        )
        return berita
    }

    fun List<ListItem>.toDataEvent(): MutableList<Any> {
        val event = mutableListOf<Any>()
        var currentDate = ""
        var position = 0
        this.forEach {
            if (!currentDate.equals(getMontYear(it.date!!))) {
                currentDate = getMontYear(it.date)
                event.add(currentDate)
            }
            event.add(
                DataEvent(
                    getPage(position, 12),
                    getId(position, 12),
                    it.title,
                    it.date,
                    it.image,
                    null,
                    false
                )
            )
            position++
            Log.d("Helpers", "toDataEvent: $position")
        }
        return event
    }

    //    Change List to Event Room
    fun List<ListItem>.toDataEventFromRoom(): MutableList<Any> {
        val event = mutableListOf<Any>()
        this.forEach {
            event.add(DataEvent(it.page, it.id, it.title, it.date, it.image, null, true))
        }
        return event
    }

    fun ObjectDetail.toDataEvent(): DataEvent {
        return DataEvent(0, 0, title, null, image, content, false)
    }

    private fun getPage(position: Int, cut: Int): Int {
        var page = 0
        if (position >= cut) page = (position / cut) + 1
        return page
    }

    private fun getId(position: Int, cut: Int): Int {
        var id = 0
        if (position >= cut) id = position % cut
        else id = position
        return id
    }

    fun getMontYear(date: String): String {
        val clear = date.split(" ").toTypedArray()
        return "${clear[1]} ${clear[2]}"
    }

    fun getMonthYearfromDMYT(date: String): String {
        val clear = date.split("/", ",").toTypedArray()
        val dateFormat =
            SimpleDateFormat("dd-MM-yyyy").parse(clear[0] + "-" + clear[1] + "-" + clear[2])
        val dateFormated = SimpleDateFormat("dd MMMM yyyy").format(dateFormat)
        return dateFormated.toString()
    }

//    ROOM

    fun DataBerita.toSimpan(): DataSave {
        Log.d("Helpers", "toSimpan: ${this.id}")
        return DataSave(
            pageid = buildString { append(this@toSimpan.page, " ", this@toSimpan.id) },
            type = "BERITA"
        )
    }

    fun DataBerita.toSimpanHolder(id: Int?): DataSave {
        Log.d("Helpers", "toSimpan: ${id}")
        return DataSave(
            id,
            pageid = buildString { append(this@toSimpanHolder.page, " ", this@toSimpanHolder.id) },
            type = "BERITA"
        )
    }

    fun DataEvent.toSimpan(): DataSave {
        return DataSave(
            pageid = buildString { append(this@toSimpan.page, " ", this@toSimpan.id) },
            type = "EVENT"
        )
    }

    fun DataEvent.toSimpanHolder(id: Int?): DataSave {
        Log.d("Helpers", "toSimpan: ${id}")
        return DataSave(
            id,
            pageid = buildString { append(this@toSimpanHolder.page, " ", this@toSimpanHolder.id) },
            type = "EVENT"
        )
    }

//    Pariwisata

    fun List<ListItem>.toDataPariwisata(): MutableList<Any> {
        val pariwisata = mutableListOf<Any>()
        this.forEachIndexed { index, listItem ->
            pariwisata.add(
                DataPariwisata(index, listItem.title, listItem.image, null, null, null, null, false)
            )
        }
        return pariwisata
    }

    fun DataPariwisata.toSimpan(): DataSave {
        return DataSave(
            pageid = buildString { append("00 ", this@toSimpan.id) },
            type = "PARIWISATA"
        )
    }

    fun List<ListItem>.toDataPariwisataFromRoom(): MutableList<Any> {
        val pariwisata = mutableListOf<Any>()
        this.forEach {
            pariwisata.add(DataPariwisata(it.id, it.title, it.image, null, null, null, null, true))
        }
        return pariwisata
    }

    fun DataPariwisata.toSimpanHolder(id: Int?): DataSave {
        Log.d("Helpers", "toSimpan: ${id}")
        return DataSave(
            id,
            pageid = buildString { append("00 ", this@toSimpanHolder.id) },
            type = "PARIWISATA"
        )
    }

//    Kuliner

    fun List<ListItem>.toDataKuliner(): MutableList<Any> {
        val kuliner = mutableListOf<Any>()
        this.forEachIndexed { index, listItem ->
            kuliner.add(
                DataKuliner(
                    index,
                    listItem.title,
                    listItem.image,
                    null,
                    null,
                    null,
                    null,
                    false
                )
            )
        }
        return kuliner
    }

    fun DataKuliner.toSimpan(): DataSave {
        return DataSave(
            pageid = buildString { append("00 ", this@toSimpan.id) },
            type = "KULINER"
        )
    }

    fun List<ListItem>.toDataKulinerFromRoom(): MutableList<Any> {
        val kuliner = mutableListOf<Any>()
        this.forEach {
            kuliner.add(
                DataKuliner(
                    it.id,
                    it.title,
                    it.image,
                    null,
                    null,
                    null,
                    null,
                    true
                )
            )
        }
        return kuliner
    }

    fun DataKuliner.toSimpanHolder(id: Int?): DataSave {
        Log.d("Helpers", "toSimpan: ${id}")
        return DataSave(
            id,
            pageid = buildString { append("00 ", this@toSimpanHolder.id) },
            type = "KULINER"
        )
    }

//    Oleh
    fun List<ListItem>.toDataOleh(): MutableList<Any> {
        val oleh = mutableListOf<Any>()
        this.forEachIndexed { index, listItem ->
            oleh.add(
                DataOleh(
                    index,
                    listItem.title,
                    listItem.image,
                    null,
                    false
                )
            )
        }
        return oleh
    }

    fun DataOleh.toSimpan(): DataSave {
        return DataSave(
            pageid = buildString { append("00 ", this@toSimpan.id) },
            type = "OLEH"
        )
    }

    fun List<ListItem>.toDataOlehFromRoom(): MutableList<Any> {
        val oleh = mutableListOf<Any>()
        this.forEach {
            oleh.add(
                DataOleh(
                    it.id,
                    it.title,
                    it.image,
                    null,
                    true
                )
            )
        }
        return oleh
    }

    fun DataOleh.toSimpanHolder(id: Int?): DataSave {
        Log.d("Helpers", "toSimpan: ${id}")
        return DataSave(
            id,
            pageid = buildString { append("00 ", this@toSimpanHolder.id) },
            type = "OLEH"
        )
    }

//    Penginapan

    fun List<ListItem>.toDataPenginapan(): MutableList<Any> {
        val penginapan = mutableListOf<Any>()
        this.forEachIndexed { index, listItem ->
            penginapan.add(
                DataPenginapan(
                    index,
                    listItem.title,
                    listItem.image,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    false
                )
            )
        }
        return penginapan
    }

    fun DataPenginapan.toSimpan(): DataSave {
        return DataSave(
            pageid = buildString { append("00 ", this@toSimpan.id) },
            type = "PENGINAPAN"
        )
    }

    fun List<ListItem>.toDataPenginapanFromRoom(): MutableList<Any> {
        val penginapan = mutableListOf<Any>()
        this.forEach {
            penginapan.add(
                DataPenginapan(
                    it.id,
                    it.title,
                    it.image,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    true
                )
            )
        }
        return penginapan
    }

    fun DataPenginapan.toSimpanHolder(id: Int?): DataSave {
        Log.d("Helpers", "toSimpan: ${id}")
        return DataSave(
            id,
            pageid = buildString { append("00 ", this@toSimpanHolder.id) },
            type = "PENGINAPAN"
        )
    }

    //    Map
    fun DataPenginapan.toMap(): DataMap = DataMap(this.title, this.address, this.lat, this.lang)

    fun DataPariwisata.toMap(): DataMap = DataMap(this.title, this.address, this.lat, this.lang)

    fun DataKuliner.toMap(): DataMap = DataMap(this.title, this.address, this.lat, this.lang)

}