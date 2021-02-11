package com.delta_se.tegalur.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import coil.load
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataOleh
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.databinding.ActivityDetailOlehBinding
import com.delta_se.tegalur.ui.fragments.SimpanViewModel
import com.delta_se.tegalur.utils.Helpers.toSimpan
import kotlinx.android.synthetic.main.content_scrolling_oleh.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailOleh : AppCompatActivity() {

    private lateinit var binding : ActivityDetailOlehBinding
    private lateinit var dataOleh : DataOleh
    private lateinit var modelDataSave : SimpanViewModel
    private val model : DetailOlehViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOlehBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataOleh = intent.getParcelableExtra<DataOleh>(EXTRA_DATA) ?: DataOleh()
        modelDataSave = SimpanViewModel(application)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = dataOleh.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        model.getOlehDetail(dataOleh.id!!)
        getDataFromDatabase {
            val simpan = it.find { sim ->
                sim.pageid.equals(
                    buildString { append("00 ", dataOleh.id) }
                )
                        &&
                        sim.type.equals("OLEH")
            }
            val isSaved = simpan != null

            binding.apply {
                if (isSaved) fabOleh.load(R.drawable.ic_item_active_mark){crossfade(true)}
                else fabOleh.load(R.drawable.ic_item_mark){crossfade(true)}

                fabOleh.setOnClickListener {
                    when (!isSaved) {
                        true -> {
                            dataOleh.isSaved = true
                            fabOleh.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                            lifecycleScope.launch {
                                modelDataSave.addToSave(dataOleh.toSimpan())
                            }
                        }
                        false -> {
                            dataOleh!!.isSaved = false
                            fabOleh.load(R.drawable.ic_item_mark) { crossfade(true) }
                            lifecycleScope.launch {
                                modelDataSave.removeFromSave(simpan!!)
                            }
                        }
                    }
                }
            }
        }

        model.oleh.observe(this){
            binding.apply {
                toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
                titleOleh.text = dataOleh.title
                ivDetailPhoto.load(dataOleh.image) { crossfade(true)}
                detailDescriptionOleh.text = it.content
            }
        }
    }

    private fun getDataFromDatabase(onDataResult: (data: List<DataSave>) -> Unit) {
        lifecycleScope.launch {
            modelDataSave.getAllSimpan().collect {
                it.observe(this@DetailOleh) { data ->
                    onDataResult(data)
                }
            }
        }
    }

    companion object {
        val EXTRA_DATA = "extra_Data"
    }
}