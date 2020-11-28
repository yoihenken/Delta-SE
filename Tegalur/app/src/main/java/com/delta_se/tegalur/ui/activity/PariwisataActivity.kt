package com.delta_se.tegalur.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.dummy.DataDummy
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter
import com.delta_se.tegalur.ui.adapter.ListPariwisataAdapter
import kotlinx.android.synthetic.main.activity_pariwisata.*

class PariwisataActivity : AppCompatActivity() {

    private val list = ArrayList<DataPariwisata>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pariwisata)

        getDataPariwsata()

        rvPariwisata.apply {
            layoutManager = LinearLayoutManager(this@PariwisataActivity)
            setHasFixedSize(true)
            adapter = ListPariwisataAdapter(list,context)
        }
    }

    private fun getDataPariwsata() : ArrayList<DataPariwisata>{
        val dataBerita : ArrayList<DataPariwisata> = DataDummy().getPariwisata()
        list.addAll(dataBerita)
        return list
    }
}