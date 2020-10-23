package com.ttmagic.tiki

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.*
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect



fun TextView.formatDiscount(discount: Int?) {
    if (discount == null || discount == 0) {
        visibility = View.INVISIBLE
    } else {
        visibility = View.VISIBLE
        text = String.format(
            context.getString(R.string.format_discount),
            discount
        )
    }
}


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

fun Int?.dpToPx(context: Context): Int {
    return (this ?: 0) * context.resources.displayMetrics.density.toInt()
}

/**
 * Post Value into a liveData when collect.
 */
suspend fun <T> Flow<T>.onCollectPostValue(liveData: MutableLiveData<T>) {
    this.collect { liveData.postValue(it) }
}
