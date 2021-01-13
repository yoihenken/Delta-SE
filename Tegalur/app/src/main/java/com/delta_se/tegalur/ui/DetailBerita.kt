package com.delta_se.tegalur.ui

import android.app.Activity
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
import com.delta_se.tegalur.databinding.ActivityDetailBeritaBinding
import kotlinx.android.synthetic.main.activity_detail_berita.*
import kotlinx.android.synthetic.main.content_scrolling.*

class DetailBerita : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBeritaBinding

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
        binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myData by getParcelableExtra<DataBerita>(DetailBerita.EXTRA_MYDATA)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = myData?.title.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        binding.apply {
            toolbarLayoutBerita.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
            titleBerita.text = myData?.title
            datePublish.text = myData?.date
            iv_detail_photo.load(myData?.image){
                crossfade(true)
            }
            detailDescription.text = myData?.description
        }
    }
}