package com.nicolasmilliard.playground.ui.home

import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nicolasmilliard.playground.R
import com.nicolasmilliard.playground.service.Item

internal class ItemResultViewHolder(
    root: View,
    private val callback: ItemResultAdapter.Callback
) : ViewHolder(root), OnClickListener {
    private val imageView: ImageView = root.findViewById(R.id.image)
    private val titleText: TextView = root.findViewById(R.id.title)
    private val secondaryText: TextView = root.findViewById(R.id.secondary)

    init {
        root.setOnClickListener(this)
    }

    private var item: Item? = null

    override fun onClick(view: View) {
        callback.onItemClicked(item!!)
    }


    fun update(itemResult: ItemResult) {
        val item = itemResult.item
        this.item = itemResult.item

        titleText.text = item.name
        secondaryText.text = item.description
    }
}
