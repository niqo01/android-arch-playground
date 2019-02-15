package com.nicolasmilliard.playground.ui.util

import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.view.LayoutInflater
import androidx.annotation.DimenRes

internal inline val Context.layoutInflater: LayoutInflater get() = LayoutInflater.from(this)

internal fun Context.getDividerInsetLeftDrawable(@DimenRes insetLeft: Int): InsetDrawable {
    val attrs = intArrayOf(android.R.attr.listDivider)
    val a = obtainStyledAttributes(attrs)
    val divider = a.getDrawable(0)
    val inset = resources.getDimensionPixelSize(insetLeft)
    val insetDivider = InsetDrawable(divider, inset, 0, 0, 0)
    a.recycle()
    return insetDivider
}