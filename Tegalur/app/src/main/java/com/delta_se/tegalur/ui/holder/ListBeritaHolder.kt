package com.delta_se.tegalur.ui.holder

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.databinding.ItemListBinding
import com.delta_se.tegalur.ui.activity.DetailBerita

class ListBeritaHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private val binding = ItemListBinding.bind(itemView)

    fun bindView(data: DataBerita, context: Context) = with(binding){
        imageList.load(data.image){
            crossfade(true)
            transformations(RoundedCornersTransformation(10f))
        }
        titleList.text = data.title
        descList.text = data.date
        data.isSaved = true //get from Local Data

        imageSimpan.setOnClickListener {
            if(data.isSaved){
                data.isSaved = false
                imageSimpan.load(R.drawable.ic_item_active_mark){crossfade(true)}
            }else{
                data.isSaved = true
                imageSimpan.load(R.drawable.ic_item_mark){crossfade(true)}
            }
        }

        itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(context, DetailBerita::class.java)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_DATABERITA, data)
//            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_MYPOSITION, position)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_TYPE, "BERITA")
            context.startActivity(moveWithObjectIntent)
        }

    }

}