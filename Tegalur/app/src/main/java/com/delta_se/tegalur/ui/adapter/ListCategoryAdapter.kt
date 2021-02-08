package com.delta_se.tegalur.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.dummy.DataDummy
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.data.model.DataPenginapan
import com.delta_se.tegalur.data.model.DataRecycler
import com.delta_se.tegalur.databinding.ItemCategoryBinding
import com.delta_se.tegalur.ui.activity.DetailBerita
import com.delta_se.tegalur.ui.activity.DetailPariwisata
import com.delta_se.tegalur.ui.activity.DetailPenginapan
import com.delta_se.tegalur.ui.fragments.TabSearchViewModel
import com.delta_se.tegalur.utils.Helpers.toSimpanHolder

class ListCategoryAdapter(
    private val listData: List<Any>,
    private val context: Context,
    private val modeAdapter: String,
    private val model : TabSearchViewModel
) : RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>() {

    private lateinit var binding: ItemCategoryBinding

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        binding = ItemCategoryBinding.bind(view)
        return ListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        when (modeAdapter) {
            "PARIWISATA" -> {
                val data : DataPariwisata = listData[position] as DataPariwisata
                binding.apply {
                    imageCategory.load(data.image) {
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleCategory.text = data.title
                    descCategory.text = data.address

                    model.savedPariw.observe( (context) as LifecycleOwner){
                        data.isSaved = false
                        var isSaved : Boolean
                        it.forEach { dataSave ->
                            isSaved = it.contains(data.toSimpanHolder(dataSave.id))
                            if (isSaved) data.isSaved = true
                        }
                    }
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true) }
                }
                holder.itemView.setOnClickListener {
                    val moveWithObjectIntent = Intent(context, DetailPariwisata::class.java)
                    moveWithObjectIntent.putExtra(DetailPariwisata.EXTRA_MYDATA, data)
                    context.startActivity(moveWithObjectIntent)
                }
            }

            "OLEH" -> {}
            "KULINER" -> {}
            "PENGINAPAN" -> {
                val data : DataPenginapan = listData[position] as DataPenginapan
                binding.apply {
                    imageCategory.load(data.image) {
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))}
                    titleCategory.text = data.title
                    descCategory.text = data.address
                    model.savedPeng.observe(context as LifecycleOwner){
                        data.isSaved = false
                        var isSaved = false
                        it.forEach { dataSave ->
                            isSaved = it.contains(data.toSimpanHolder(dataSave.id))
                            if (isSaved) data.isSaved = true
                        }
                    }
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true) }
                }
                holder.itemView.setOnClickListener {
                    val moveWithObjectIntent = Intent(context, DetailPenginapan::class.java)
                    moveWithObjectIntent.putExtra(DetailPenginapan.EXTRA_MYDATA, data)
                    context.startActivity(moveWithObjectIntent)
                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size
}