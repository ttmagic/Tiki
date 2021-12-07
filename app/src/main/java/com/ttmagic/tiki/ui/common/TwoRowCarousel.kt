package com.ttmagic.tiki.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT, saveViewState = true)
class TwoRowCarousel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Carousel(context, attrs, defStyleAttr) {

    @DrawableRes
    var backgroundRes: Int? = null
        @CallbackProp set

    init {
        isNestedScrollingEnabled = false
        setDefaultGlobalSnapHelperFactory(null)
    }

    override fun createLayoutManager(): LayoutManager {
        setItemSpacingDp(5)
        return GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
    }

    @AfterPropsSet
    fun useProps() {
        backgroundRes?.let { this.setBackgroundResource(it) }
    }
}