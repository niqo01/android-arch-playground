package com.nicolasmilliard.playground.ui.home

import android.view.View
import android.widget.ViewAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.nicolasmilliard.playground.R
import com.nicolasmilliard.playground.presenter.Model
import com.nicolasmilliard.playground.api.Item
import com.nicolasmilliard.playground.ui.UiBinder
import com.nicolasmilliard.playground.ui.util.displayedChildId
import com.nicolasmilliard.playground.ui.util.getDividerInsetLeftDrawable
import com.nicolasmilliard.playground.ui.util.layoutInflater
import com.squareup.picasso3.Picasso

class HomeUiBinder(
    view: View,
    picasso: Picasso,
    onClick: ItemHandler
) : UiBinder<Model> {
    private val context = view.context

    private val viewAnimator = view as ViewAnimator
    private val results: RecyclerView = view.findViewById(R.id.results)
    private val shimmer: ShimmerFrameLayout = view.findViewById(R.id.shimmer_view_container)

    private val resultsAdapter =
        ItemResultAdapter(context.layoutInflater, picasso, object : ItemResultAdapter.Callback {
            override fun onItemClicked(item: Item) = onClick(item)
        })

    init {
        results.adapter = resultsAdapter

        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        dividerItemDecoration.setDrawable(context.getDividerInsetLeftDrawable(R.dimen.divider_padding_72))

        results.layoutManager = layoutManager
        results.addItemDecoration(dividerItemDecoration)
    }

    override fun bind(model: Model, oldModel: Model?) {
        if (model.isLoading) {
            shimmer.startShimmer()
            viewAnimator.displayedChildId = R.id.shimmer_view_container
        } else {
            viewAnimator.displayedChildId = R.id.results
            shimmer.stopShimmer()
        }

        val data = model.data
        val itemResults = data.map { ItemResult(it) }
        resultsAdapter.submitList(itemResults)
    }
}