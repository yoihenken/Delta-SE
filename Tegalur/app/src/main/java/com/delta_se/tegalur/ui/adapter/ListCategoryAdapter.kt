package com.delta_se.tegalur.ui.adapter

import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.delta_se.tegalur.R
import com.delta_se.tegalur.SearchActivity
import com.delta_se.tegalur.data.dummy.DataDummy
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.data.model.DataRecycler
import com.delta_se.tegalur.databinding.ItemCategoryBinding
import com.delta_se.tegalur.ui.DetailBerita
import com.delta_se.tegalur.ui.DetailPariwisata

class ListCategoryAdapter(
    val listData: ArrayList<DataRecycler>,
    val activity: Activity,
    val modeCategory: String
) : RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>() {

    private lateinit var binding: ItemCategoryBinding

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view)

    var arrDataBerita = ArrayList<DataBerita>()
    var arrDataPariwisata = ArrayList<DataPariwisata>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        binding = ItemCategoryBinding.bind(view)
        return ListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        convertData(position)

        when (modeCategory) {
            "BERITA" -> {
                var data = arrDataBerita[position]
                binding.apply {
                    imageCategory.load(data.image) {
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleCategory.text = data.title
                    descCategory.text = data.date
                    data.isSaved = true //get from Local Data
                    imageSimpan.setOnClickListener {
                        if (data.isSaved == true) {
                            data.isSaved = false
                            imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                        } else {
                            data.isSaved = true
                            imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true) }
                        }
                    }
                }
                holder.itemView.setOnClickListener {
                    val moveWithObjectIntent = Intent(activity, DetailBerita::class.java)
                    moveWithObjectIntent.putExtra(DetailBerita.EXTRA_MYDATA, data)
                    activity?.startActivity(moveWithObjectIntent)
                }
            }
            "PARIWISATA" -> {
                var data = arrDataPariwisata[position]
                binding.apply {
                    imageCategory.load(data.image) {
                        crossfade(true)
                        transformations(RoundedCornersTransformation(10f))
                    }
                    titleCategory.text = data.title
                    descCategory.text = data.address
                    data.isSaved = true //get from Local Data
                    imageSimpan.setOnClickListener {
                        if (data.isSaved == true) {
                            data.isSaved = false
                            imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true) }
                        } else {
                            data.isSaved = true
                            imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true) }
                        }
                    }
                }
                holder.itemView.setOnClickListener {
                    val moveWithObjectIntent = Intent(activity, DetailPariwisata::class.java)
                    moveWithObjectIntent.putExtra(DetailPariwisata.EXTRA_MYDATA, data)
                    activity?.startActivity(moveWithObjectIntent)
                }
            }
        }
    }


    private fun convertData(position: Int) {
        when (modeCategory) {
            "BERITA" -> {
                var dataDummy = DataDummy().getDataBerita()
                val item = listData[position]

                arrDataBerita.add(
                    DataBerita(
                        item.title,
                        item.image,
                        item.desc,
                        dataDummy[listData.indexOf(item)].description,
                        dataDummy[listData.indexOf(item)].isSaved
                    )
                )
            }
            "PARIWISATA" -> {
                var dataDummy = DataDummy().getPariwisata()
                val item = listData[position]
                arrDataPariwisata.add(
                    DataPariwisata(
                        item.title,
                        item.image,
                        item.desc,
                        dataDummy[listData.indexOf(item)].content,
                        dataDummy[listData.indexOf(item)].lat,
                        dataDummy[listData.indexOf(item)].lang,
                        dataDummy[listData.indexOf(item)].isSaved
                    )
                )
            }
        }
    }


    override fun getItemCount(): Int = listData.size
}