package com.delta_se.tegalur.utils

import android.util.Log
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataEvent
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.data.response.ObjectDetail
import java.text.SimpleDateFormat

object Helpers {
    fun List<ListItem>.toDataBerita(): MutableList<Any> {
        val berita = mutableListOf<Any>()
        var currentDate = ""
        var position = 0
        this.forEach {
            if (!currentDate.equals(getMonthYearfromDMYT(it.date!!))){
                currentDate = getMonthYearfromDMYT(it.date)
                berita.add(currentDate)
            }
            berita.add(
                DataBerita(getPage(position, 15), getId(position, 15), it.title, it.img, it.date, null, null, false)
            )
            position++
        }
        return berita
    }

    fun ObjectDetail.toDataBerita(): MutableList<DataBerita>{
        val berita = mutableListOf<DataBerita>()
        berita.add(
            DataBerita(0, 0, title, img, tanggal, penulis, isi, false)
        )
        return berita
    }

    fun List<ListItem>.toDataEvent(): MutableList<Any>{
        val event = mutableListOf<Any>()
        var currentDate = ""
        var position = 0
        this.forEach {
            if (!currentDate.equals(getMontYear(it.date!!))){
                currentDate = getMontYear(it.date)
                event.add(currentDate)
            }
            event.add(DataEvent(getPage(position,12), getId(position, 12),  it.title, it.date, it.image, null, false))
            position++
            Log.d("Helpers", "toDataEvent: $position")
        }
        return event
    }

    fun ObjectDetail.toDataEvent(): DataEvent {
        return DataEvent(0, 0, title, null, image, content, false)
    }

    private fun getPage(position : Int, cut : Int): Int {
        var page = 0
        if (position >= cut) page = (position / cut) + 1
        return page
    }

    private fun getId(position : Int, cut : Int): Int {
        var id = 0
        if (position >= cut) id  = position % cut
        else id = position
        return id
    }

    fun getMontYear(date : String): String {
        val clear = date.split(" ").toTypedArray()
        return "${clear[1].toString()} ${clear[2].toString()}"
    }

    fun getMonthYearfromDMYT(date : String): String {
        val clear = date.split("/", ",").toTypedArray()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy").parse(clear[0] + "-" + clear[1] + "-" + clear[2])
        val dateFormated = SimpleDateFormat("dd MMMM yyyy").format(dateFormat)
        return dateFormated.toString()
    }
}