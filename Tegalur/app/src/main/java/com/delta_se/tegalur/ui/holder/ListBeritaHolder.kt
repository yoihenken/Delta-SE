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
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.databinding.ItemListBinding
import com.delta_se.tegalur.ui.activity.DetailBerita
import com.delta_se.tegalur.ui.fragments.BeritaViewModel
import com.delta_se.tegalur.utils.Helpers.toSimpan
import com.delta_se.tegalur.utils.Helpers.toSimpanHolder

class ListBeritaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemListBinding.bind(itemView)

    fun bindView(data: DataBerita, context: Context, model: BeritaViewModel) = with(binding) {
        imageList.load(data.image) {
            crossfade(true)
            transformations(RoundedCornersTransformation(10f))
        }
        titleList.text = data.title
        descList.text = data.date
        data.isSaved = true //get from Local Data

        var isSaved = false

        model.saved.observe((context as Activity) as LifecycleOwner, {
            it.forEachIndexed { index, dataSave ->
                isSaved = it.contains(data.toSimpanHolder(it[index].id))
                Log.d("ListBeritaHolder", "saved1: $it")
                Log.d("ListBeritaHolder", "saved1: ${data.toSimpanHolder(index)}")
            }
            if (isSaved) imageSimpan.load(R.drawable.ic_item_active_mark) { crossfade(true)}
            else imageSimpan.load(R.drawable.ic_item_mark) { crossfade(true)}
            Log.d("ListBeritaHolder", "saved1: $isSaved")
        })

        itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(context, DetailBerita::class.java)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_DATABERITA, data)
            moveWithObjectIntent.putExtra(DetailBerita.EXTRA_TYPE, "BERITA")
            context.startActivity(moveWithObjectIntent)
        }
    }
}