package com.delta_se.tegalur.ui.activity

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataEvent
import com.delta_se.tegalur.data.response.ObjectDetail
import com.delta_se.tegalur.databinding.ActivityDetailBeritaBinding
import com.delta_se.tegalur.utils.Helpers.toDataEvent
import kotlinx.android.synthetic.main.activity_detail_berita.*
import kotlinx.android.synthetic.main.content_scrolling.*

class DetailBerita : AppCompatActivity() {
    private val model: DetailBeritaViewModel by viewModels()
    private lateinit var binding : ActivityDetailBeritaBinding

    private var page = 1
    private var id = 1

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DATABERITA = "extra_databerita"
        const val EXTRA_DATAEVENT = "extra_dataevent"
        const val EXTRA_MYPOSITION = "extra_myposition"
        const val EXTRA_TYPE = "extra_type"
    }

    inline fun <reified T : Parcelable> Activity.getParcelableExtra(key: String) = lazy {
        intent.getParcelableExtra<T>(key)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val modeAdapter = intent.getStringExtra(EXTRA_TYPE)
        val dataBerita by getParcelableExtra<DataBerita>(EXTRA_DATABERITA)
        val dataEvent by getParcelableExtra<DataEvent>(EXTRA_DATAEVENT)
        val position = intent.getIntExtra(EXTRA_MYPOSITION, 0)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (dataBerita!=null){

            supportActionBar?.title = dataBerita?.title.toString()

            if (modeAdapter != null) firstChooseType(modeAdapter, position, dataBerita, null)

            var buttonFloat = findViewById<FloatingActionButton>(R.id.fab)
            buttonFloat.setOnClickListener { view ->
                if (dataBerita?.isSaved == true) {
                    dataBerita?.isSaved = false
                    buttonFloat.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                } else {
                    dataBerita?.isSaved = true
                    buttonFloat.load(R.drawable.ic_item_mark) { crossfade(true) }
                }
            }
        }else {

            supportActionBar?.title = dataEvent?.title.toString()

            if (modeAdapter != null) firstChooseType(modeAdapter, position, null, dataEvent)

            var buttonFloat = findViewById<FloatingActionButton>(R.id.fab)
            buttonFloat.setOnClickListener { view ->
                if (dataEvent?.isSaved == true) {
                    dataEvent?.isSaved = false
                    buttonFloat.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                } else {
                    dataEvent?.isSaved = true
                    buttonFloat.load(R.drawable.ic_item_mark) { crossfade(true) }
                }
            }
        }
    }

    private fun populateDataAdapter(it: ObjectDetail?, dataBerita: DataBerita ?= null, dataEvent: DataEvent ?= null) = with(binding) {
        toolbarLayoutBerita.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)

        when(intent.getStringExtra(EXTRA_TYPE)){
            "BERITA" -> {
                titleBerita.text = it?.title
                datePublish.text = it?.tanggal
                iv_detail_photo.load(it?.img){
                    crossfade(true)
                }
                detailDescription.text = it?.isi
            }
            "EVENT" -> {
                titleBerita.text = it?.title
                ivDetailPhoto.load(it?.image){
                    crossfade(true)
                }
                datePublish.text = dataEvent?.date
                detailDescription.text = it?.content
            }
        }
    }

    private fun getPageId(position : Int, total : Int){
        if (position >= total){
            page = (position / total) + 1
            id  = position % total

        }else id = position

        Log.d("DetailBerita", "getBeritaDetail: $id , $page, $position")

    }

    private fun firstChooseType(modeAdapter : String, position: Int, dataBerita: DataBerita ?= null, dataEvent: DataEvent ?= null) = when(modeAdapter){

        "BERITA" -> {
            getPageId(position, 15)
            model.getBeritaDetail(page, id)
            model.berita.observe(this, {
                populateDataAdapter(it)
            })
        }
        "EVENT" -> {
            model.getEventDetail(dataEvent?.page!!, dataEvent.id!!)
            model.event.observe(this, {
                populateDataAdapter(it, null, dataEvent)
            })
        }
        else -> {}
    }
}