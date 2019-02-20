package com.nicolasmilliard.playground.ui.home

import android.graphics.drawable.Drawable
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nicolasmilliard.playground.R
import com.nicolasmilliard.playground.api.Item
import com.nicolasmilliard.playground.ui.util.RoundedTransformation
import com.squareup.picasso3.Picasso

internal class ItemResultViewHolder(
    root: View,
    private val picasso: Picasso,
    private val callback: ItemResultAdapter.Callback
) : ViewHolder(root), OnClickListener {

    private val context = root.context
    private val accountIcon: Drawable = context.getDrawable(R.drawable.ic_account_circle_black_40dp)!!
    private val roundedTransformation: RoundedTransformation

    private val imageView: ImageView = root.findViewById(R.id.image)
    private val titleText: TextView = root.findViewById(R.id.title)
    private val secondaryText: TextView = root.findViewById(R.id.secondary)

    init {
        root.setOnClickListener(this)
        accountIcon.setTint(ContextCompat.getColor(context, R.color.icon_disable_light))

        val imageSize = context.resources.getDimension(R.dimen.item_two_line_image_size)
        roundedTransformation = RoundedTransformation(imageSize)
    }

    private var item: Item? = null

    override fun onClick(view: View) {
        callback.onItemClicked(item!!)
    }

    fun update(resultItem: ResultItem) {
        val item = resultItem.item
        this.item = resultItem.item

        titleText.text = item.name
        secondaryText.text = item.description

        picasso.load(item.imageUrl)
            .placeholder(accountIcon)
//            .transform(roundedTransformation) TODO Issue with HARDWARE bitmap
            .noFade()
            .into(imageView)
    }
}
