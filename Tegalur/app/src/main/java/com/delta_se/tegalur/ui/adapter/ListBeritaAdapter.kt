package com.delta_se.tegalur.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.R
import com.delta_se.tegalur.databinding.ItemListBinding
import com.delta_se.tegalur.ui.activity.DetailBerita
import com.delta_se.tegalur.ui.holder.HeaderHolder
import com.delta_se.tegalur.ui.holder.ListBeritaHolder
import java.lang.IllegalArgumentException

class ListBeritaAdapter (
    private val listData : List<Any>, val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = getLayout(viewType)
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return getViewHolder(viewType, view)
    }

    private fun getViewHolder(viewType: Int, view: View): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_HEADER -> HeaderHolder(view)
            ITEM_LIST -> ListBeritaHolder(view)
            else -> throw IllegalArgumentException("Undefined viewtype")
        }
    }

    private fun getLayout(viewType: Int): Int {
        return when(viewType){
            ITEM_HEADER -> R.layout.item_header
            ITEM_LIST -> R.layout.item_list
            else -> throw IllegalArgumentException("Undefined viewtype")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(getItemViewType(position)){
           ITEM_HEADER -> (holder as HeaderHolder).bindContent(listData[position] as String)
           ITEM_LIST -> (holder as ListBeritaHolder).bindView(listData[position] as DataBerita, context)
           else -> throw IllegalArgumentException("Undefined viewtype")
       }
    }

    override fun getItemCount(): Int = listData.size

    override fun getItemViewType(position: Int): Int {
        return when(listData[position]){
            is String -> ITEM_HEADER
            is DataBerita -> ITEM_LIST
            else -> Log.d("ListBeritaAdapter", "getLayout: Undefined viewType")
        }
    }

    init {
        setHasStableIds(true);
    }

    companion object{
        private val ITEM_HEADER = 0
        private val ITEM_LIST = 1
    }
}