package com.opening.openingassignment.ui.fragment

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.opening.network.model.TopLink
import com.opening.openingassignment.Utils.dateConverter
import com.opening.openingassignment.databinding.ItemBottomViewBinding
import com.opening.openingassignment.databinding.ItemTopLinksBinding

class TopLinksAdapter(val listener: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val list = mutableListOf<TopLink>()
    inner class ViewHolder(val binding: ItemTopLinksBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind() {
            val topLink = list[adapterPosition]
            binding.tvTitle.text = topLink.title
            binding.tvDate.text = dateConverter(topLink.created_at)
            binding.tvClickNum.text = topLink.total_clicks.toString()
            binding.tvLink.text = topLink.smart_link
            Glide.with(binding.ivMedia.context).load(topLink.original_image).into(binding.ivMedia)
            binding.tvLink.setOnClickListener {
                listener.invoke(topLink.smart_link)
            }
        }
    }

    inner class BottomViewHolder(val binding: ItemBottomViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        if (viewType == 1) {
            val binding = ItemBottomViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BottomViewHolder(binding)
        }
        val binding = ItemTopLinksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> holder.bind()
            is BottomViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == list.size - 1) {
            return 1
        }
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(items: List<TopLink>) {
        list.addAll(items)
        // bottom view
        list.add(TopLink("","","",false,"","","","","",0,0,"","",""))
        notifyDataSetChanged()
    }
}