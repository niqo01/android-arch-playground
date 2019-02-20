package com.nicolasmilliard.playground.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nicolasmilliard.playground.api.Item
import com.squareup.picasso3.Picasso

internal class ItemResultAdapter(
    private val inflater: LayoutInflater,
    private val picasso: Picasso,
    private val callback: Callback
) : ListAdapter<HomeItem, RecyclerView.ViewHolder>(ItemResultItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val homeViewType = VIEWTYPE.values()[viewType]
        val view = inflater.inflate(homeViewType.layout, parent, false)
        return when (homeViewType) {
            VIEWTYPE.ITEM -> ItemResultViewHolder(view, picasso, callback)
            VIEWTYPE.TITLE -> TitleViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        when (holder) {
            is TitleViewHolder -> holder.update(item as HeaderItem)
            is ItemResultViewHolder -> holder.update(item as ResultItem)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType.ordinal
    }

    interface Callback {
        fun onItemClicked(item: Item)
    }

    private object ItemResultItemCallback : DiffUtil.ItemCallback<HomeItem>() {
        override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return when {
                newItem is HeaderItem && oldItem is HeaderItem -> true
                newItem is ResultItem && oldItem is ResultItem -> oldItem.item.id == newItem.item.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: HomeItem, newItem: HomeItem): Any? {
            if (oldItem == newItem) {
                return null
            }
            return Unit // Dummy value to prevent item_two_lines_material change animation.
        }
    }
}