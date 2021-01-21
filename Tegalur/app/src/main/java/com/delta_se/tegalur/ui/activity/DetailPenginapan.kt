package com.delta_se.tegalur.ui.activity

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataPenginapan
import com.delta_se.tegalur.databinding.ActivityDetailPenginapanBinding
import kotlinx.android.synthetic.main.content_detail_penginapan.*
import kotlinx.android.synthetic.main.content_detail_penginapan.isiAlamat
import kotlinx.android.synthetic.main.content_detail_penginapan.isiDeskripsi
import kotlinx.android.synthetic.main.content_detail_penginapan.isiTelepon
import kotlinx.android.synthetic.main.content_detail_penginapan.isiWebsite

class DetailPenginapan : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPenginapanBinding

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
        binding = ActivityDetailPenginapanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myData by getParcelableExtra<DataPenginapan>(EXTRA_MYDATA)
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
            toolbarLayoutPenginapan.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
            titlePenginapan.text = myData?.title
            photoPenginapan.load(myData?.image){
                crossfade(true)
            }
            isiAlamat.text = myData?.address
            isiTelepon.text = myData?.phone.toString()
            isiWebsite.text = myData?.website
            isiDeskripsi.text = myData?.content
        }
    }
}