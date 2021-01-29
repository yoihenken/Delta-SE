package com.delta_se.tegalur.ui.holder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataEvent
import com.delta_se.tegalur.databinding.ItemListBinding
import com.delta_se.tegalur.ui.activity.DetailBerita
import com.delta_se.tegalur.ui.fragments.EventViewModel
import com.delta_se.tegalur.utils.Helpers.toSimpan
import com.delta_se.tegalur.utils.Helpers.toSimpanHolder

class ListEventHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemListBinding.bind(itemView)

    fun bindView(data: DataEvent, context: Context, model: EventViewModel) = with(binding) {
        imageList.load(data.image){
            crossfade(true)
            transformations(RoundedCornersTransformation(10f))
        }
        titleList.text = data.title
        descList.text = data.date
        data.isSaved = true //get from local data

        var isSaved = false

        model.saved.observe((context as Activity) as LifecycleOwner, {
            it.forEachIndexed { index, dataSave ->
                isSaved = it.contains(data.toSimpanHolder(it[index].id))
                Log.d("ListEventHolder", "saved1: $it")
                Log.d("ListEventHolder", "saved1: ${data.toSimpanHolder(index)}")
            }
            Log.d("ListEventHolder", "bindView: $isSaved")
            if (isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true) }
            else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true) }
        })

//        imageSimpan.setOnClickListener {
//            if (!data.isSaved){
//                data.isSaved = true
//                imageSimpan.load(R.drawable.ic_item_active_mark){crossfade(true)}
//            }else{
//                data.isSaved = false
//                imageSimpan.load(R.drawable.ic_item_mark){crossfade(true)}
//            }
//        }

        itemView.setOnClickListener{
            val moveWithObjectIntent = Intent(context, DetailBerita::class.java)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_DATAEVENT, data)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_TYPE, "EVENT")
            context.startActivity(moveWithObjectIntent)
        }
    }
}