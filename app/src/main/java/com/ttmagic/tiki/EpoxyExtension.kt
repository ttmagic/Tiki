package com.ttmagic.tiki

import com.airbnb.epoxy.CarouselModelBuilder
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.ttmagic.tiki.ui.AutoScrollCarouselModelBuilder
import com.ttmagic.tiki.ui.TwoRowCarouselModelBuilder

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
inline fun <T> CarouselModelBuilder.withModelsFrom(
    items: List<T>,
    modelBuilder: (T) -> EpoxyModel<*>
) {
    models(items.map { modelBuilder(it) })
}

inline fun <T> CarouselModelBuilder.withModelsFromIndexed(
    items: List<T>,
    modelBuilder: (Int, T) -> EpoxyModel<*>
) {
    models(items.mapIndexed { index, item -> modelBuilder(index, item) })
}

inline fun <T> AutoScrollCarouselModelBuilder.withModelsFrom(
    items: List<T>,
    modelBuilder: (T) -> EpoxyModel<*>
) {
    models(items.map { modelBuilder(it) })
}

inline fun <T> AutoScrollCarouselModelBuilder.withModelsFromIndexed(
    items: List<T>,
    modelBuilder: (Int, T) -> EpoxyModel<*>
) {
    models(items.mapIndexed { index, item -> modelBuilder(index, item) })
}


inline fun <T> TwoRowCarouselModelBuilder.withModelsFrom(
    items: List<T>,
    modelBuilder: (T) -> EpoxyModel<*>
) {
    models(items.map { modelBuilder(it) })
}

inline fun <T> TwoRowCarouselModelBuilder.withModelsFromIndexed(
    items: List<T>,
    modelBuilder: (Int, T) -> EpoxyModel<*>
) {
    models(items.mapIndexed { index, item -> modelBuilder(index, item) })
}