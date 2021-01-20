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
import com.delta_se.tegalur.data.model.DataEvent
import com.delta_se.tegalur.databinding.ItemListBinding
import com.delta_se.tegalur.ui.DetailBerita

class ListEventAdapter (
    private val listData : List<DataEvent>, val context: Context
) : RecyclerView.Adapter<ListEventAdapter.ListViewHolder>() {
    class ListViewHolder (view: View) : RecyclerView.ViewHolder(view){}

    private lateinit var binding : ItemListBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListEventAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        binding = ItemListBinding.bind(view)
        return ListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ListEventAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        binding.apply {
            imageList.load(data.image){
                crossfade(true)
                transformations(RoundedCornersTransformation(10f))
            }
            titleList.text = data.title
            descList.text = data.date
            data.isSaved = true

            imageSimpan.setOnClickListener {
                if (data.isSaved == true){
                    data.isSaved = false
                    imageSimpan.load(R.drawable.ic_item_active_mark){crossfade(true)}
                }else{
                    data.isSaved = true
                    imageList.load(R.drawable.ic_item_mark){crossfade(true)}
                }
            }
        }

        holder.itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(context, DetailBerita::class.java)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_DATAEVENT, data)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_MYPOSITION, position)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_TYPE, "EVENT")
            context.startActivity(moveWithObjectIntent)
        }

    }

    override fun getItemCount(): Int = listData.size

    init {
        setHasStableIds(true)
    }
}