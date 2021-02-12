package com.delta_se.tegalur.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.*
import com.delta_se.tegalur.databinding.ItemCategoryBinding
import com.delta_se.tegalur.databinding.ItemListBinding
import com.delta_se.tegalur.ui.activity.*

class ListSimpanAdapter(
    private val listData: List<Any>,
    private val activity : Activity,
    private val modeAdapter : String
) : RecyclerView.Adapter<ListSimpanAdapter.ListSimpanHolder>() {

    class ListSimpanHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var bindingList : ItemListBinding
    private lateinit var bindingCategory : ItemCategoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSimpanHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        if (modeAdapter == "BERITA" || modeAdapter == "EVENT") {
            bindingList = ItemListBinding.bind(view)
            return ListSimpanHolder(bindingList.root)
        }else {
            bindingCategory = ItemCategoryBinding.bind(view)
            return ListSimpanHolder(bindingCategory.root)
        }
    }

    override fun onBindViewHolder(holder: ListSimpanHolder, position: Int) {
        when(modeAdapter){
            "BERITA" -> {
                val data: DataBerita = listData[position] as DataBerita
                Log.d("ListSimpanAdapter", "dataBerita: ${data}")
                bindingList.apply {
                    imageList.load(data.image) {
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleList.text = data.title
                    descList.text = data.date
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) {
                        crossfade(
                            true
                        )
                    }
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true) }

                    itemSelect.setOnClickListener {

                        val moveWithObjectIntent = Intent(activity, DetailBerita::class.java)
                        moveWithObjectIntent.putExtra(DetailBerita.EXTRA_DATABERITA, data)
                        moveWithObjectIntent.putExtra(DetailBerita.EXTRA_TYPE, "BERITA")
                        activity.startActivity(moveWithObjectIntent)
                        Log.d("ListSimpanAdapter", "Clicked: $data")
                    }
                }
            }
            "EVENT"-> {
                val data : DataEvent = listData[position] as DataEvent
                bindingList.apply {
                    imageList.load(data.image){
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleList.text = data.title
                    descList.text = data.date
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true)}
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true)}

                    itemSelect.setOnClickListener{
                        val moveWithObjectIntent = Intent(activity, DetailBerita::class.java)
                        moveWithObjectIntent.putExtra(DetailBerita.EXTRA_DATAEVENT, data)
                        moveWithObjectIntent.putExtra(DetailBerita.EXTRA_TYPE, "EVENT")
                        activity.startActivity(moveWithObjectIntent)
                    }
                }
            }
            "PARIWISATA" -> {
                val data : DataPariwisata = listData[position] as DataPariwisata
                bindingCategory.apply {
                    imageCategory.load(data.image){
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleCategory.text = data.title
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true)}
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true)}

                    itemSelect.setOnClickListener{
                        val moveWithObjectIntent = Intent(activity, DetailPariwisata::class.java)
                        moveWithObjectIntent.putExtra(DetailPariwisata.EXTRA_MYDATA, data)
                        activity.startActivity(moveWithObjectIntent)
                    }
                }
            }
            "OLEH"-> {
                val data : DataOleh = listData[position] as DataOleh
                bindingCategory.apply {
                    imageCategory.load(data.image) {
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleCategory.text = data.title
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true)}
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true)}

                    itemSelect.setOnClickListener{
                        val moveWithObjectIntent = Intent(activity, DetailOleh::class.java)
                        moveWithObjectIntent.putExtra(DetailOleh.EXTRA_DATA, data)
                        activity.startActivity(moveWithObjectIntent)
                    }
                }
            }
            "KULINER" -> {
                val data : DataKuliner = listData[position] as DataKuliner
                bindingCategory.apply {
                    imageCategory.load(data.image) {
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleCategory.text = data.title
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true)}
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true)}

                    itemSelect.setOnClickListener{
                        val moveWithObjectIntent = Intent(activity, DetailKuliner::class.java)
                        moveWithObjectIntent.putExtra(DetailKuliner.EXTRA_DATA, data)
                        activity.startActivity(moveWithObjectIntent)
                    }
                }
            }
            "PENGINAPAN" -> {
                val data : DataPenginapan = listData[position] as DataPenginapan
                bindingCategory.apply {
                    imageCategory.load(data.image){
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleCategory.text = data.title
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true)}
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true)}

                    itemSelect.setOnClickListener{
                        val moveWithObjectIntent = Intent(activity, DetailPenginapan::class.java)
                        moveWithObjectIntent.putExtra(DetailPenginapan.EXTRA_MYDATA, data)
                        activity.startActivity(moveWithObjectIntent)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size

}