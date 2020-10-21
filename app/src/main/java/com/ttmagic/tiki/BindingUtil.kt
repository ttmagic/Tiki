package com.ttmagic.tiki

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*
import androidx.databinding.BindingAdapter


@BindingAdapter("app:addStatusBarPadding")
fun View.setAddStatusBarPadding(addPadding: Boolean) {
    if (!addPadding) return
    this.updateLayoutParams<ViewGroup.MarginLayoutParams> {
        val top = marginTop + context.getStatusBarHeight()
        setMargins(marginLeft, top, marginRight, marginBottom)
    }
}


fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}