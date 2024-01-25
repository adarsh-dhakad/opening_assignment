package com.opening.openingassignment.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opening.openingassignment.databinding.ItemDashboardCardBinding
import com.opening.openingassignment.ui.model.TrendingItem

class TrendingItemAdapter() : RecyclerView.Adapter<TrendingItemAdapter.ViewHolder>() {
    val list = mutableListOf<TrendingItem>()
    fun addItems(items: List<TrendingItem>) {
        list.addAll(items)
        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding: ItemDashboardCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.ivMedia.setImageResource(list[adapterPosition].icon)
            binding.tvTitle.text = list[adapterPosition].title
            binding.tvSubtitle.text = list[adapterPosition].subTitle
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemDashboardCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}