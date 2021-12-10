package com.ttmagic.tiki.ui.common

import androidx.annotation.ColorRes
import com.airbnb.epoxy.*
import com.ttmagic.tiki.R
import com.ttmagic.tiki.loading

//This will find all layouts that starts with "epoxy" then it generates EpoxyBindingModel for the layout.
@EpoxyDataBindingPattern(rClass = R::class, layoutPrefix = "epoxy")
interface EpoxyBindingConfig

/** For use in the buildModels method of EpoxyController. A shortcut for creating a Carousel model, initializing it, and adding it to the controller.
 *
 */
inline fun EpoxyController.carousel(modelInitializer: CarouselModelBuilder.() -> Unit) {
    CarouselModel_().apply {
        modelInitializer()
    }.addTo(this)
}

/** Add models to a CarouselModel_ by transforming a list of items into EpoxyModels.
 *
 * @param items The items to transform to models
 * @param modelBuilder A function that take an item and returns a new EpoxyModel for that item.
 */
inline fun <T> CarouselModelBuilder.map(
    items: List<T>,
    modelBuilder: (T) -> EpoxyModel<*>
) {
    models(items.map { modelBuilder(it) })
}

inline fun <T> CarouselModelBuilder.mapIndexed(
    items: List<T>,
    modelBuilder: (Int, T) -> EpoxyModel<*>
) {
    models(items.mapIndexed { index, item -> modelBuilder(index, item) })
}

inline fun <T> AutoScrollCarouselModelBuilder.mapIndexed(
    items: List<T>,
    modelBuilder: (Int, T) -> EpoxyModel<*>
) {
    models(items.mapIndexed { index, item -> modelBuilder(index, item) })
}


inline fun <T> TwoRowCarouselModelBuilder.mapIndexed(
    items: List<T>,
    modelBuilder: (Int, T) -> EpoxyModel<*>
) {
    models(items.mapIndexed { index, item -> modelBuilder(index, item) })
}

inline fun <T> TwoRowGridModelBuilder.mapIndexed(
    items: List<T>,
    modelBuilder: (Int, T) -> EpoxyModel<*>
) {
    models(items.mapIndexed { index, item -> modelBuilder(index, item) })
}

fun EpoxyController.divider(heightDp: Int = 10, @ColorRes color: Int = R.color.light_gray) {
    divider {
        id("divider")
        heightDp(10)
        backgroundRes(R.color.light_gray)
    }
}

fun EpoxyController.loading() {
    loading {
        id("loading")
    }
}