package com.delta_se.tegalur.ui.activity

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import coil.load
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataKuliner
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.databinding.ActivityDetailKulinerBinding
import com.delta_se.tegalur.ui.fragments.SimpanViewModel
import com.delta_se.tegalur.utils.Helpers.toMap
import com.delta_se.tegalur.utils.Helpers.toSimpan
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.android.synthetic.main.content_scrolling_pariwisata.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailKuliner : AppCompatActivity() {

    private lateinit var binding : ActivityDetailKulinerBinding
    private lateinit var dataKuliner : DataKuliner
    private lateinit var modelDataSave: SimpanViewModel
    private val model : DetailKulinerViewModel by viewModels()

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKulinerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataKuliner = intent.getParcelableExtra<DataKuliner>(EXTRA_DATA) ?: DataKuliner()

        modelDataSave = SimpanViewModel(application)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = dataKuliner.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        geoCoder()
        model.getKulinerDetail(dataKuliner.id!!)
        getDataFromDatabase {
            val simpan = it.find { sim ->
                sim.pageid.equals(
                    buildString { append("00 ", dataKuliner?.id) }
                )
                        &&
                    sim.type.equals("KULINER")
            }
            val isSaved = simpan != null

            binding.apply {
                if (isSaved) fabKuliner.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                else fabKuliner.load(R.drawable.ic_item_mark)

                fabKuliner.setOnClickListener {
                    when (!isSaved) {
                        true -> {
                            dataKuliner.isSaved = true
                            fabKuliner.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                            lifecycleScope.launch {
                                modelDataSave.addToSave(dataKuliner.toSimpan())
                            }
                        }
                        false -> {
                            dataKuliner!!.isSaved = false
                            fabKuliner.load(R.drawable.ic_item_mark) { crossfade(true) }
                            lifecycleScope.launch {
                                modelDataSave.removeFromSave(simpan!!)
                            }
                        }
                    }
                }
            }

            model.kuliner.observe(this){
                binding.apply {
                    toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
                    titlePariwisata.text = dataKuliner.title
                    ivDetailPhoto.load(dataKuliner.image) {
                        crossfade(true)
                    }
                    isiAlamat.text = it.address
                    isiDeskripsi.text = it.content
                }
            }
        }
    }

    fun geoCoder(){
        val geoCoder = Geocoder(this)
        val dataAddress = geoCoder.getFromLocationName(dataKuliner?.title.toString(), 1)

        dataKuliner.lat = dataAddress[0].latitude
        dataKuliner.lang = dataAddress[0].longitude

        var buttonFloatMap = findViewById<ExtendedFloatingActionButton>(R.id.efabPariwisata)
        buttonFloatMap.setOnClickListener { view ->
            Log.d("DetailPariwisata", "setOnClickListener: ${dataKuliner.toMap()}")
            startActivity(Intent(this, MapsActivity::class.java).apply {
                putExtra(MapsActivity.EXTRA_MYDATA, dataKuliner.toMap())
            })
        }
    }

    private fun getDataFromDatabase(onDataResult: (data: List<DataSave>) -> Unit) {
        lifecycleScope.launch {
            modelDataSave.getAllSimpan().collect {
                it.observe(this@DetailKuliner) { data ->
                    onDataResult(data)
                }
            }
        }
    }

    companion object {
        val EXTRA_DATA = "extra_Data"
    }
}