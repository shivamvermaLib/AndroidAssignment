package com.abc.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abc.app.databinding.SublistItemLayoutBinding

class SublistAdapter(private val sublistItems: Map<String, Int>) :
    RecyclerView.Adapter<SublistAdapter.SublistViewHolder>() {

    class SublistViewHolder(val sublistItemLayoutBinding: SublistItemLayoutBinding) :
        RecyclerView.ViewHolder(sublistItemLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SublistViewHolder {
        val binding =
            SublistItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SublistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SublistViewHolder, position: Int) {
        val key = sublistItems.keys.elementAt(position)
        val count = sublistItems[key]
        holder.sublistItemLayoutBinding.sublistItemText.text = "$key = $count"
    }

    override fun getItemCount() = sublistItems.keys.size
}