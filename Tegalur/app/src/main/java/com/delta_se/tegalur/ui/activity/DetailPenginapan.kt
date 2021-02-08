package com.delta_se.tegalur.ui.activity

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import coil.load
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataPenginapan
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.databinding.ActivityDetailPenginapanBinding
import com.delta_se.tegalur.ui.fragments.SimpanViewModel
import com.delta_se.tegalur.utils.Helpers.toSimpan
import kotlinx.android.synthetic.main.content_detail_penginapan.*
import kotlinx.android.synthetic.main.content_detail_penginapan.isiAlamat
import kotlinx.android.synthetic.main.content_detail_penginapan.isiDeskripsi
import kotlinx.android.synthetic.main.content_detail_penginapan.isiTelepon
import kotlinx.android.synthetic.main.content_detail_penginapan.isiWebsite
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailPenginapan : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPenginapanBinding
    private val model : DetailPenginapanViewModel by viewModels()
    private lateinit var modelDataSave : SimpanViewModel

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
        modelDataSave = SimpanViewModel(application)
        val myData by getParcelableExtra<DataPenginapan>(EXTRA_MYDATA)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = myData?.title.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


//        Geo Coder
        val geocoder = Geocoder(this)
        var dataAddress = geocoder.getFromLocationName(myData?.title.toString(), 1)

        myData?.lat = dataAddress[0].latitude
        myData?.lang = dataAddress[0].longitude

        binding.fabMapPeng.setOnClickListener {
            val moveWithObjectIntent = Intent(this, MapsActivity::class.java)
            moveWithObjectIntent.putExtra(MapsActivity.EXTRA_MYDATA, myData)
            startActivity(moveWithObjectIntent)
        }

        model.getPenginapanDetail(myData?.id!!)

        getDataFromDatabase {

            val simpan = it.find { sim ->
                sim.pageid.equals(
                    buildString { append("00 ", myData?.id) }
                )
                        &&
                sim.type.equals("PENGINAPAN")
            }

            val isSaved = simpan != null

            binding.apply {
                if (isSaved) fabMarkPeng.load(R.drawable.ic_item_active_mark){crossfade(true)}
                else fabMarkPeng.load(R.drawable.ic_item_mark)

                fabMarkPeng.setOnClickListener {
                    when (!isSaved){
                        true -> {
                            myData!!.isSaved = true
                            fabMarkPeng.load(R.drawable.ic_item_active_mark){crossfade(true)}
                            lifecycleScope.launch{
                                modelDataSave.addToSave(myData!!.toSimpan())
                            }
                        }
                        false -> {
                            myData!!.isSaved = false
                            fabMarkPeng.load(R.drawable.ic_item_mark) { crossfade(true) }
                            lifecycleScope.launch {
                                modelDataSave.removeFromSave(simpan!!)
                            }
                        }
                    }
                }
            }

        }

        model.penginapan.observe(this){
            binding.apply {
                toolbarLayoutPenginapan.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
                titlePenginapan.text = myData?.title
                photoPenginapan.load(myData?.image){
                    crossfade(true)
                }
                isiAlamat.text = it.address
                isiTelepon.text = it.phone.toString()
                isiHarga.text = it.price
                isiWebsite.text = it.website
                isiDeskripsi.text = it.content
            }
        }
    }

    private fun getDataFromDatabase(onDataResult : (data : List<DataSave>) -> Unit){
        lifecycleScope.launch {
            modelDataSave.getAllSimpan().collect {
                it.observe(this@DetailPenginapan){ data ->
                    onDataResult(data)
                }
            }
        }
    }
}