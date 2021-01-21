package com.delta_se.tegalur.ui.holder

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataEvent
import com.delta_se.tegalur.databinding.ItemListBinding
import com.delta_se.tegalur.ui.activity.DetailBerita

class ListEventHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemListBinding.bind(itemView)

    fun bindView(data : DataEvent, context: Context) = with(binding) {
        imageList.load(data.image){
            crossfade(true)
            transformations(RoundedCornersTransformation(10f))
        }
        titleList.text = data.title
        descList.text = data.date
        data.isSaved = true

        imageSimpan.setOnClickListener {
            if (data.isSaved){
                data.isSaved = false
                imageSimpan.load(R.drawable.ic_item_active_mark){crossfade(true)}
            }else{
                data.isSaved = true
                imageList.load(R.drawable.ic_item_mark){crossfade(true)}
            }
        }

        itemView.setOnClickListener{
            val moveWithObjectIntent = Intent(context, DetailBerita::class.java)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_DATAEVENT, data)
//            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_MYPOSITION, position)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_TYPE, "EVENT")
            context.startActivity(moveWithObjectIntent)
        }
    }
}