package com.nicolasmilliard.playground.ui.home

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicolasmilliard.playground.R
import com.nicolasmilliard.playground.presenter.Model
import com.nicolasmilliard.playground.service.Item
import com.nicolasmilliard.playground.ui.UiBinder
import com.nicolasmilliard.playground.ui.util.getDividerInsetLeftDrawable
import com.nicolasmilliard.playground.ui.util.layoutInflater


class HomeUiBinder(
    view: View
) : UiBinder<Model> {
    private val context = view.context
    private val resources = view.resources

    private val constraintLayout = view as ConstraintLayout
    private val results: RecyclerView = view.findViewById(R.id.results)

    private val resultsAdapter = ItemResultAdapter(context.layoutInflater, object : ItemResultAdapter.Callback {
        override fun onItemClicked(item: Item) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    })

    init {
        constraintLayout.loadLayoutDescription(R.xml.home_states)
        results.adapter = resultsAdapter


        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        dividerItemDecoration.setDrawable(context.getDividerInsetLeftDrawable(R.dimen.divider_padding_72))

        results.layoutManager = layoutManager
        results.addItemDecoration(dividerItemDecoration)
    }

    override fun bind(model: Model, oldModel: Model?) {
        constraintLayout.apply {
            when {
                model.isLoading -> setState(R.id.loading, 0, 0)
                !model.isLoading -> setState(R.id.home, 0, 0)
            }
        }

        val data = model.data
        val itemResults = data.map { ItemResult(it) }
        resultsAdapter.submitList(itemResults)
    }
}