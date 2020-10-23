package com.ttmagic.tiki.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT, saveViewState = true)
class AutoScrollCarousel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Carousel(context, attrs, defStyleAttr) {

    init {
        isNestedScrollingEnabled = false
        startAutoScroll()
    }

    private fun startAutoScroll() = GlobalScope.launch {
        flow {
            while (true) {
                delay(3000)
                emit(Unit)
            }
        }.flowOn(Dispatchers.IO).collect {
            var firstPos: Int =
                (layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?: 0
            val lastItemIndex = (adapter?.itemCount ?: 0) - 1
            if (firstPos == lastItemIndex) firstPos = -1
            smoothScrollToPosition(firstPos + 1)
        }
    }
}