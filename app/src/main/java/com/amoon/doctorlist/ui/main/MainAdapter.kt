package com.amoon.doctorlist.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amoon.doctorlist.R
import com.amoon.doctorlist.data.model.DoctorDetails
import com.amoon.doctorlist.databinding.MainAdapterItemsBinding
import com.amoon.doctorlist.databinding.SeenAdapterItemsBinding


class MainAdapter(private val listener: OnClickListener, private var seen: List<DoctorDetails>) :
    PagedListAdapter<DoctorDetails, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<DoctorDetails>() {
        override fun areItemsTheSame(oldItem: DoctorDetails, newItem: DoctorDetails): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: DoctorDetails, newItem: DoctorDetails): Boolean {
            return oldItem.id == newItem.id
        }
    }) {

    private val Headers = arrayListOf("Recent Doctors : ", "Vivy Doctors : ")
    private var sSize = 0

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 || (sSize in 1..3 && position == sSize + 1)) {
            R.layout.section_header
        } else if ((sSize == 1 && position in 1..2) || (sSize == 2 && position in 1..3) || (sSize == 3 && position in 1..3)) {
            R.layout.seen_adapter_items
        } else {
            R.layout.main_adapter_items
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.section_header -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.section_header,
                        parent,
                        false
                    )
            )

            R.layout.seen_adapter_items -> SeenViewHolder(
                listener,
                SeenAdapterItemsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.main_adapter_items -> MainViewHolder(
                listener,
                MainAdapterItemsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            R.layout.section_header -> (holder as HeaderViewHolder).bind(
                when {
                    seen.isEmpty() -> Headers[1]
                    position == 0 -> Headers[0]
                    else -> Headers[1]
                }
            )

            R.layout.seen_adapter_items -> (holder as SeenViewHolder).bind(
                if (seen.isNotEmpty() && seen[position - 1] != null) {
                    seen[position - 1]
                } else {
                    null
                }


            )
            R.layout.main_adapter_items -> (holder as MainViewHolder).bind(
                if (seen.isNotEmpty())
                    getItem(position - (sSize + 1))
                else
                    getItem(position - sSize )
            )
        }
        listener
    }

    fun updateData(seen: List<DoctorDetails>) {
        this.seen = seen
        sSize = this.seen.size
        this.notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(doctorDetails: DoctorDetails)
    }


}