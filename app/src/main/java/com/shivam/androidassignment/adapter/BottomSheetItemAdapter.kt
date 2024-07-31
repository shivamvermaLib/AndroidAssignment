package com.shivam.androidassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivam.androidassignment.R
import com.shivam.androidassignment.data.models.BottomSheetItem


class BottomSheetItemAdapter(private val items: List<BottomSheetItem>) :
    RecyclerView.Adapter<BottomSheetItemAdapter.BottomSheetItemViewHolder>() {

    class BottomSheetItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.item_title)
        val sublistRecyclerView: RecyclerView = itemView.findViewById(R.id.sublist_recycler_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bottom_sheet_list_item, parent, false)
        return BottomSheetItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BottomSheetItemViewHolder, position: Int) {
        val item = items[position]
        holder.titleView.text = "List ${position + 1}(${item.count})"
        holder.sublistRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.sublistRecyclerView.adapter = SublistAdapter(item.characterCount)
    }

    override fun getItemCount() = items.size
}

class SublistAdapter(private val sublistItems: Map<String, Int>) :
    RecyclerView.Adapter<SublistAdapter.SublistViewHolder>() {

    class SublistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sublistItemText: TextView = itemView.findViewById(R.id.sublist_item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SublistViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sublist_item_layout, parent, false)
        return SublistViewHolder(view)
    }

    override fun onBindViewHolder(holder: SublistViewHolder, position: Int) {
        val key = sublistItems.keys.elementAt(position)
        val count = sublistItems[key]
        holder.sublistItemText.text = "$key = $count"
    }

    override fun getItemCount() = sublistItems.keys.size
}