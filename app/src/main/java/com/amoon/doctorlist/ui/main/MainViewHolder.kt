package com.amoon.doctorlist.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.amoon.doctorlist.data.model.DoctorDetails
import com.amoon.doctorlist.databinding.MainAdapterItemsBinding
import com.thekhaeng.pushdownanim.PushDownAnim

class MainViewHolder(
    private val listener: MainAdapter.OnClickListener,
    private val binding: MainAdapterItemsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DoctorDetails?) {

        binding.items = item

        Glide.with(itemView).load(binding.items?.photoId).into(binding.image)
        PushDownAnim.setPushDownAnimTo(itemView).setScale(PushDownAnim.MODE_STATIC_DP, 5F)
            .setOnClickListener {
                binding.items?.let { it1 -> listener.onItemClick(it1) }
            }

    }
}