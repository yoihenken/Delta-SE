package com.delta_se.tegalur.ui.activity

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import coil.load
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.databinding.ActivityDetailPariwisataBinding
import com.delta_se.tegalur.ui.fragments.SimpanViewModel
import com.delta_se.tegalur.utils.Helpers.toMap
import com.delta_se.tegalur.utils.Helpers.toSimpan
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.android.synthetic.main.content_scrolling_pariwisata.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailPariwisata : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPariwisataBinding
    private val model: DetailPariwisataViewModel by viewModels()
    private lateinit var modelDataSave: SimpanViewModel

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
        modelDataSave = SimpanViewModel(application)

        val myData by getParcelableExtra<DataPariwisata>(EXTRA_MYDATA)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = myData?.title.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        model.getPariwisataDetail(myData?.id!!)

        getDataFromDatabase {
            val simpan = it.find { sim ->
                sim.pageid.equals(
                    buildString { append("00 ", myData?.id) }
                )
                        &&
                        sim.type.equals("PARIWISATA")
            }

            val isSaved = simpan != null

            binding.apply {
                if (isSaved) fab.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                else fab.load(R.drawable.ic_item_mark)

                fab.setOnClickListener {
                    when (!isSaved) {
                        true -> {
                            myData!!.isSaved = true
                            fab.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                            lifecycleScope.launch {
                                modelDataSave.addToSave(myData!!.toSimpan())
                            }
                        }
                        false -> {
                            myData!!.isSaved = false
                            fab.load(R.drawable.ic_item_mark) { crossfade(true) }
                            lifecycleScope.launch {
                                modelDataSave.removeFromSave(simpan!!)
                            }
                        }
                    }
                }
            }
        }

        model.pariwisata.observe(this) {
            binding.apply {
                toolbarLayoutPariwisata.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
                titlePariwisata.text = myData?.title
                ivDetailPhoto.load(myData?.image) {
                    crossfade(true)
                }
                isiAlamat.text = it.address
                isiDeskripsi.text = it.content
            }
        }
        geoCoder(myData)
    }

    private fun getDataFromDatabase(onDataResult: (data: List<DataSave>) -> Unit) {
        lifecycleScope.launch {
            modelDataSave.getAllSimpan().collect {
                it.observe(this@DetailPariwisata) { data ->
                    onDataResult(data)
                }
            }
        }
    }

    private fun geoCoder(myData : DataPariwisata?){
        //        Geo Coder
        val geoCoder = Geocoder(this)
        var dataAddress = geoCoder.getFromLocationName(myData?.title.toString(), 1)

        if (dataAddress.isNotEmpty()){
            myData?.lat = dataAddress[0].latitude
            myData?.lang = dataAddress[0].longitude

            binding.efabPariwisata.setOnClickListener{
                Log.d("DetailPariwisata", "setOnClickListener: ${myData?.toMap()}")
                startActivity(Intent(this, MapsActivity::class.java).apply {
                    putExtra(MapsActivity.EXTRA_MYDATA, myData?.toMap())
                })
            }
        } else {
            binding.efabPariwisata.visibility = View.GONE
        }
    }

}