package com.amoon.doctorlist.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.amoon.doctorlist.data.model.DoctorDetails
import com.amoon.doctorlist.databinding.SeenAdapterItemsBinding
import com.thekhaeng.pushdownanim.PushDownAnim

class SeenViewHolder(
    private val listener: MainAdapter.OnClickListener,
    private val binding: SeenAdapterItemsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DoctorDetails?) {

        binding.seenItems = item
        Glide.with(itemView).load(binding.seenItems?.photoId).into(binding.image)
        PushDownAnim.setPushDownAnimTo(itemView).setScale(PushDownAnim.MODE_STATIC_DP, 5F)
            .setOnClickListener {
                binding.seenItems?.let { it1 -> listener.onItemClick(it1) }
            }

    }
}