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
import com.ttmagic.tiki.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT, saveViewState = true)
class TwoRowGrid @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Carousel(context, attrs, defStyleAttr) {

    @DrawableRes
    var backgroundRes: Int? = R.color.white
        @CallbackProp set

    init {
        isNestedScrollingEnabled = false
        backgroundRes?.let { setBackgroundResource(it) }
    }

    override fun createLayoutManager(): LayoutManager {
        return GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
    }

    @AfterPropsSet
    fun useProps() {
        backgroundRes?.let { this.setBackgroundResource(it) }
    }
}