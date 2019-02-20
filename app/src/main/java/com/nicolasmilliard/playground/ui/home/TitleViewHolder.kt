package com.nicolasmilliard.playground.ui.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class TitleViewHolder(root: View) : RecyclerView.ViewHolder(root) {
    private val titleText = root as TextView

    fun update(headerItem: HeaderItem) {
        titleText.text = headerItem.title
    }
}