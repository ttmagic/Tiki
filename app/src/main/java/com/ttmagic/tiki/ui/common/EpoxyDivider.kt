package com.ttmagic.tiki.ui.common

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.ttmagic.tiki.dpToPx

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT, fullSpan = true)
class EpoxyDivider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @DrawableRes
    var backgroundRes: Int? = null
        @CallbackProp set

    var heightDp: Int? = 1
        @CallbackProp set

    var startMarginDp: Int? = 0
        @CallbackProp set

    var endMarginDp: Int? = 0
        @CallbackProp set

    init {
        this.setBackgroundColor(Color.GRAY)
    }


    @AfterPropsSet
    fun useProps() {
        layoutParams.height = heightDp.dpToPx(context)
        requestLayout()

        val params = MarginLayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(startMarginDp.dpToPx(context), 0, endMarginDp.dpToPx(context), 0)
        params.height = layoutParams.height

        layoutParams = params
        layoutParams

        backgroundRes?.let { this.setBackgroundResource(it) }
    }
}