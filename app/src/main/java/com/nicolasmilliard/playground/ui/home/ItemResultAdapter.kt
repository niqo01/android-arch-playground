package com.nicolasmilliard.playground.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nicolasmilliard.playground.R
import com.nicolasmilliard.playground.service.Item

internal class ItemResultAdapter(
    private val inflater: LayoutInflater,
    private val callback: Callback
) : ListAdapter<ItemResult, ItemResultViewHolder>(ItemResultItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemResultViewHolder {
        val view = inflater.inflate(R.layout.item_two_lines_material, parent, false)
        return ItemResultViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ItemResultViewHolder, position: Int) {
        holder.update(currentList[position])
    }

    interface Callback {
        fun onItemClicked(item: Item)
    }

    private object ItemResultItemCallback : DiffUtil.ItemCallback<ItemResult>() {
        override fun areItemsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
            return oldItem.item.id == newItem.item.id
        }

        override fun areContentsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: ItemResult, newItem: ItemResult): Any? {
            if (oldItem == newItem) {
                return null
            }
            return Unit // Dummy value to prevent item_two_lines_material change animation.
        }
    }
}