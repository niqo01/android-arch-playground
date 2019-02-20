package com.nicolasmilliard.playground.ui.home

import com.nicolasmilliard.playground.R
import com.nicolasmilliard.playground.api.Item

sealed class HomeItem(val viewType: VIEWTYPE)
internal data class HeaderItem(val title: String) : HomeItem(VIEWTYPE.TITLE)
internal data class ResultItem(val item: Item) : HomeItem(VIEWTYPE.ITEM)

enum class VIEWTYPE(val layout: Int) {
    TITLE(R.layout.item_title_material),
    ITEM(R.layout.item_two_lines_material)
}