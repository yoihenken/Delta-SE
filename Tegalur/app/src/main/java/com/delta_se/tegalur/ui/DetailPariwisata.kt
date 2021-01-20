package com.delta_se.tegalur.ui

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
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
import com.delta_se.tegalur.databinding.ActivityDetailPariwisataBinding
import com.delta_se.tegalur.ui.activity.MapsActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.android.synthetic.main.content_scrolling_pariwisata.*

class DetailPariwisata : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPariwisataBinding

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
        binding = ActivityDetailPariwisataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myData by getParcelableExtra<DataPariwisata>(DetailPariwisata.EXTRA_MYDATA)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = myData?.title.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val geoCoder = Geocoder(this)
        var dataAddress = geoCoder.getFromLocationName(myData?.title.toString(),1)

        myData?.lat = dataAddress[0].latitude
        myData?.lang = dataAddress[0].longitude

        var buttonExFloat = findViewById<ExtendedFloatingActionButton>(R.id.efabPariwisata)
        buttonExFloat.setOnClickListener { view ->
            val moveWithObjectIntent = Intent(this,MapsActivity::class.java)
            moveWithObjectIntent.putExtra(MapsActivity.EXTRA_MYDATA, myData)
            startActivity(moveWithObjectIntent)
        }

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
            toolbarLayoutPariwisata.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
            titlePariwisata.text = myData?.title
            ivDetailPhoto.load(myData?.image){
                crossfade(true)
            }
            isiAlamat.text = myData?.address
            isiDeskripsi.text = myData?.content
        }
    }
}