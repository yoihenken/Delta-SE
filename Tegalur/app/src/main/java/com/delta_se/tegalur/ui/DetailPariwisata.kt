package com.delta_se.tegalur.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.ui.activity.MapsActivity
import kotlinx.android.synthetic.main.activity_detail_berita.*
import kotlinx.android.synthetic.main.activity_detail_berita.iv_detail_photo
import kotlinx.android.synthetic.main.activity_detail_pariwisata.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.detail_description
import kotlinx.android.synthetic.main.content_scrolling_pariwisata.*

class DetailPariwisata : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_MYDATA = "extra_mydata"
    }

    inline fun <reified T : Parcelable> Activity.getParcelableExtra(key: String) = lazy {
        intent.getParcelableExtra<T>(key)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pariwisata)

        val myData by getParcelableExtra<DataPariwisata>(DetailPariwisata.EXTRA_MYDATA)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = myData?.title.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = myData?.title.toString()

        var buttonFloat = findViewById<FloatingActionButton>(R.id.fab)
        buttonFloat.setOnClickListener { view ->
            val moveWithObjectIntent = Intent(this,MapsActivity::class.java)
            moveWithObjectIntent.putExtra(MapsActivity.EXTRA_MYDATA, myData)
            startActivity(moveWithObjectIntent)
        }

        Glide
            .with(this)
            .load(myData?.image.toString())
            .apply(RequestOptions().override(700, 700))
            .into(iv_detail_photo)

        isiDeskripsi.text = myData?.content
    }
}