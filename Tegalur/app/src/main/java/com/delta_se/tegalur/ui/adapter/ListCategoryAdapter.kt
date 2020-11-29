package com.delta_se.tegalur.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataRecycler
import com.delta_se.tegalur.databinding.ItemCategoryBinding

class ListCategoryAdapter(
    val listData : ArrayList<DataRecycler>
) : RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>() {

    private lateinit var binding : ItemCategoryBinding
    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        binding = ItemCategoryBinding.bind(view)
        return ListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        binding.apply {
            imageCategory.load(data.image){
                crossfade(true)
            }
            titleCategory.text = data.title
            descCategory.text = data.desc
            data.isSaved = true //get from Local Data
            imageSimpan.setOnClickListener {
                if(data.isSaved == true){
                    data.isSaved = false
                    imageSimpan.load(R.drawable.ic_item_active_mark){crossfade(true)}
                }else{
                    data.isSaved = true
                    imageSimpan.load(R.drawable.ic_item_mark){crossfade(true)}
                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size
}