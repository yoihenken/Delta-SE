package com.delta_se.tegalur.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataEvent
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.databinding.ItemCategoryBinding
import com.delta_se.tegalur.ui.activity.DetailBerita

class ListSimpanAdapter(
    private val listData: List<Any>,
    private val context: Context,
    private val modeAdapter : String
) : RecyclerView.Adapter<ListSimpanAdapter.ListSimpanHolder>() {

    class ListSimpanHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding : ItemCategoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSimpanHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        binding = ItemCategoryBinding.bind(view)
        return ListSimpanHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ListSimpanHolder, position: Int) {
        when(modeAdapter){
            "BERITA"-> {
                var data : DataBerita = listData[position] as DataBerita
                binding.apply {
                    imageCategory.load(data.image) {
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleCategory.text = data.title
                    descCategory.text = data.date
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true)}
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true)}

                    holder.itemView.setOnClickListener{
                        val moveWithObjectIntent = Intent(context, DetailBerita::class.java)
                        moveWithObjectIntent.putExtra(DetailBerita.EXTRA_DATABERITA, data)
                        moveWithObjectIntent.putExtra(DetailBerita.EXTRA_TYPE, "BERITA")
                        context.startActivity(moveWithObjectIntent)
                    }
                }
            }
            "EVENT"-> {
                val data : DataEvent = listData[position] as DataEvent
                binding.apply {
                    imageCategory.load(data.image){
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleCategory.text = data.title
                    descCategory.text = data.date
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true)}
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true)}

                    holder.itemView.setOnClickListener{
                        val moveWithObjectIntent = Intent(context, DetailBerita::class.java)
                        moveWithObjectIntent.putExtra(DetailBerita.EXTRA_DATAEVENT, data)
                        moveWithObjectIntent.putExtra(DetailBerita.EXTRA_TYPE, "EVENT")
                        context.startActivity(moveWithObjectIntent)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size

}