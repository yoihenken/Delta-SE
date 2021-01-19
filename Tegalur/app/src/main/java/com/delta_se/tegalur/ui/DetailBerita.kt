package com.delta_se.tegalur.ui

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.response.ObjectDetail
import com.delta_se.tegalur.databinding.ActivityDetailBeritaBinding
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter
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
        const val EXTRA_MYDATA = "extra_mydata"
        const val EXTRA_MYPOSITION = "extra_myposition"
    }

    inline fun <reified T : Parcelable> Activity.getParcelableExtra(key: String) = lazy {
        intent.getParcelableExtra<T>(key)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myData by getParcelableExtra<DataBerita>(DetailBerita.EXTRA_MYDATA)
        val position = intent.getIntExtra(EXTRA_MYPOSITION, 0)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = myData?.title.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getPageId(position)

        model.getBeritaDetail(page, id)
        model.berita.observe(this, {
            populateDataBerita(it)
        })

        var buttonFloat = findViewById<FloatingActionButton>(R.id.fab)
        buttonFloat.setOnClickListener { view ->
            if (myData?.isSaved == true) {
                myData?.isSaved = false
                buttonFloat.load(R.drawable.ic_item_active_mark) { crossfade(true) }
            } else {
                myData?.isSaved = true
                buttonFloat.load(R.drawable.ic_item_mark) { crossfade(true) }
            }
        }
    }

    private fun populateDataBerita(it: ObjectDetail?) = with(binding) {
        toolbarLayoutBerita.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        titleBerita.text = it?.title
        datePublish.text = it?.tanggal
        iv_detail_photo.load(it?.img){
            crossfade(true)
        }
        detailDescription.text = it?.isi
    }

    private fun getPageId(position : Int){
        if (position >= 15){
            page = (position / 15) + 1
            id  = position % 15

        }else id = position

        Log.d("DetailBerita", "getBeritaDetail: $id , $page, $position")

    }
}