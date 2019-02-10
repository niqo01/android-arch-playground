package com.nicolasmilliard.playground.ui.util

import android.widget.ViewAnimator

internal inline var ViewAnimator.displayedChildId: Int
    get() = getChildAt(displayedChild).id
    set(value) {
        if (displayedChildId == value) {
            return
        }
        var i = 0
        val count = childCount
        while (i < count) {
            if (getChildAt(i).id == value) {
                displayedChild = i
                return
            }
            i++
        }
        val name = resources.getResourceEntryName(value)
        throw IllegalArgumentException("No view with ID $name")
    }
