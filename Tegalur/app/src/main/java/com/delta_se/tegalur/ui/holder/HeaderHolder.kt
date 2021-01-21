package com.delta_se.tegalur.ui.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.delta_se.tegalur.R
import kotlinx.android.synthetic.main.item_header.view.*

class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemHeader = itemView.findViewById(R.id.tvHeaderItem) as TextView

    fun bindContent(text : String){
        itemHeader.text = text
    }
}