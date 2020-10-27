package com.ttmagic.tiki

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.view.*
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


@BindingAdapter("app:glideSrc")
fun ImageView.setGlideSrc(@DrawableRes drawableRes: Int) {
    Glide.with(context)
        .load(drawableRes)
        .into(this)
}

@BindingAdapter("app:imgUrl")
fun ImageView.setImgUrl(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

@BindingAdapter("app:formatDiscount")
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

@BindingAdapter("app:formatPrice")
fun TextView.formatPrice(price: Int) {
    text = String.format(resources.getString(R.string.format_price), price)
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
