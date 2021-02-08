package com.delta_se.tegalur.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.dummy.DataDummy
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.data.model.DataRecycler
import com.delta_se.tegalur.databinding.ItemCategoryBinding
import com.delta_se.tegalur.ui.activity.DetailBerita
import com.delta_se.tegalur.ui.activity.DetailPariwisata

class ListCategoryAdapter(
    val listData: List<Any>,
    val activity: Activity,
    val modeAdapter: String
) : RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>() {

    private lateinit var binding: ItemCategoryBinding

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view)

    var arrDataBerita = ArrayList<DataBerita>()
    var arrDataPariwisata = ArrayList<DataPariwisata>()

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
                    if (data.isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) {
                        crossfade(
                            true
                        )
                    }
                    else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true) }
                }
                holder.itemView.setOnClickListener {
                    val moveWithObjectIntent = Intent(activity, DetailPariwisata::class.java)
                    moveWithObjectIntent.putExtra(DetailPariwisata.EXTRA_MYDATA, data)
                    activity.startActivity(moveWithObjectIntent)
                }
            }
        }
    }


//    private fun convertData(position: Int) {
//        when (modeCategory) {
//            "PARIWISATA" -> {
//                var dataDummy = DataDummy().getPariwisata()
//                val item = listData[position]
//                arrDataPariwisata.add(
//                    DataPariwisata(
//                        item.id,
//                        item.title,
//                        item.image,
//                        item.desc,
//                        dataDummy[listData.indexOf(item)].content,
//                        dataDummy[listData.indexOf(item)].lat,
//                        dataDummy[listData.indexOf(item)].lang,
//                        dataDummy[listData.indexOf(item)].isSaved
//                    )
//                )
//            }
//        }
//    }


    override fun getItemCount(): Int = listData.size
}