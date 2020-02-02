package com.amoon.doctorlist.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.section_header.view.*

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: String?) {

       itemView.secTitle.text = item

    }
}