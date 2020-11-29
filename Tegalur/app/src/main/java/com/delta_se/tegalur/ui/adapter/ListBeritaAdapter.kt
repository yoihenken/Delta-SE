package com.delta_se.tegalur.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.R
import com.delta_se.tegalur.databinding.ItemListBinding
import com.delta_se.tegalur.ui.DetailBerita

class ListBeritaAdapter (
    private val listData : ArrayList<DataBerita>, val context: Context
        ) : RecyclerView.Adapter<ListBeritaAdapter.ListViewHolder>(){

    private lateinit var binding : ItemListBinding
    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBeritaAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        binding = ItemListBinding.bind(view)
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
        holder.itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(context, DetailBerita::class.java)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_MYDATA, data)
            context.startActivity(moveWithObjectIntent)
        }
    }

    override fun getItemCount(): Int = listData.size
}