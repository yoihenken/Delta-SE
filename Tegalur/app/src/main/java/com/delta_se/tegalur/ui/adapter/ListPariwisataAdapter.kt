package com.delta_se.tegalur.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.databinding.ItemListBinding
import com.delta_se.tegalur.ui.DetailBerita
import com.delta_se.tegalur.ui.DetailPariwisata

class ListPariwisataAdapter (
    private val listData : ArrayList<DataPariwisata>, val context: Context
    ) : RecyclerView.Adapter<ListPariwisataAdapter.ListViewHolder>(){

        private lateinit var binding : ItemListBinding
        class ListViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPariwisataAdapter.ListViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            binding = ItemListBinding.bind(view)
            return ListViewHolder(binding.root)
        }

        override fun onBindViewHolder(holder: ListPariwisataAdapter.ListViewHolder, position: Int) {
            val data = listData[position]
            binding.apply {
                imageBerita.load(data.image){
                    crossfade(true)
                }
                titleBerita.text = data.title
                dateBerita.text = data.address

                imageSimpan.setOnClickListener {
                    data.isSaved = true //get from Local Data
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
                val moveWithObjectIntent = Intent(context, DetailPariwisata::class.java)
                moveWithObjectIntent.putExtra(DetailBerita.EXTRA_MYDATA, data)
                context.startActivity(moveWithObjectIntent)
            }
        }

        override fun getItemCount(): Int = listData.size
}