package com.delta_se.tegalur.ui.activity

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import coil.load
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.databinding.ActivityDetailPariwisataBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.android.synthetic.main.content_scrolling_pariwisata.*

class DetailPariwisata : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPariwisataBinding
    private val model : DetailPariwisataViewModel by viewModels()

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

        val myData by getParcelableExtra<DataPariwisata>(EXTRA_MYDATA)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = myData?.title.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        model.getPariwisataDetail(myData?.id!!)

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


        model.pariwisata.observe(this){
            binding.apply {
                toolbarLayoutPariwisata.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
                titlePariwisata.text = myData?.title
                ivDetailPhoto.load(myData?.image){
                    crossfade(true)
                }
                isiAlamat.text = it.address
                isiDeskripsi.text = it.content
            }
        }
    }
}