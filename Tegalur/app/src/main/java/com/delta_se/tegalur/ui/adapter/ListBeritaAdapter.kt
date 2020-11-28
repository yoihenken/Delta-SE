package com.delta_se.tegalur.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.R
import com.delta_se.tegalur.databinding.ItemBeritaBinding

class ListBeritaAdapter (
    private val listData : ArrayList<DataBerita>,
        ) : RecyclerView.Adapter<ListBeritaAdapter.ListViewHolder>(){

    private lateinit var binding : ItemBeritaBinding
    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBeritaAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        binding = ItemBeritaBinding.bind(view)
        return ListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ListBeritaAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        binding.apply {
            imageBerita.load(data.image){
                crossfade(true)
            }
            titleBerita.text = data.title
            dateBerita.text = data.date
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