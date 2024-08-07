package com.abc.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abc.app.data.models.BottomSheetItem
import com.abc.app.databinding.BottomSheetListItemBinding


class BottomSheetItemAdapter(private val items: List<BottomSheetItem>) :
    RecyclerView.Adapter<BottomSheetItemAdapter.BottomSheetItemViewHolder>() {

    class BottomSheetItemViewHolder(val binding: BottomSheetListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetItemViewHolder {
        return BottomSheetItemViewHolder(
            BottomSheetListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BottomSheetItemViewHolder, position: Int) {
        val item = items[position]
        holder.binding.itemTitle.text = "List ${position + 1}(${item.count})"
        holder.binding.sublistRecyclerView.apply {
            layoutManager = LinearLayoutManager(holder.itemView.context)
            adapter = SublistAdapter(item.characterCount)
        }
    }

    override fun getItemCount() = items.size
}

