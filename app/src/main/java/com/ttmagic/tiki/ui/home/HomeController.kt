package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.EpoxyController
import com.ttmagic.tiki.*
import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.base.data
import com.ttmagic.tiki.model.*
import com.ttmagic.tiki.ui.common.*
import javax.inject.Inject

class HomeController @Inject constructor() : EpoxyController(),
    OnItemClick {

    var onBannerClick: ((Banner) -> Unit)? = null
    var onQuickLinkClick: ((QuickLink) -> Unit)? = null
    var onFlashDealClick: ((FlashDeal) -> Unit)? = null
    var onCategoryClick: ((Category) -> Unit)? = null
    var onProductClick: ((Product) -> Unit)? = null

    var homeState: HomeViewState? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        buildBanners()
        buildQuickLinks()
        buildFlashDeal()
        buildCategories()
        buildProducts()
    }

    override fun onItemClick(item: Any) {
        when (item) {
            is Banner -> onBannerClick?.invoke(item)
            is QuickLink -> onQuickLinkClick?.invoke(item)
            is FlashDeal -> onFlashDealClick?.invoke(item)
            is Category -> onCategoryClick?.invoke(item)
            is Product -> onProductClick?.invoke(item)
        }
    }

    private fun buildBanners() {
        when (homeState?.listBanners) {
            is Result.Success -> {
                autoScrollCarousel {
                    id("banner_carousel")
                    withModelsFromIndexed(homeState!!.listBanners!!.data!!) { i, item ->
                        BannerBindingModel_()
                            .id("banner_$i")
                            .banner(item)
                            .callback(this@HomeController)
                    }
                }
            }
            is Result.Error -> {///Do nothing
            }
            else -> {
                carousel {
                    id("banner_carousel")
                    withModelsFrom(arrayListOf(Banner())) {
                        BannerBindingModel_()
                            .id("banner_loading")
                            .banner(Banner())
                            .callback(this@HomeController)
                    }
                }
            }
        }
    }

    private fun buildQuickLinks() {
        when (homeState?.listQuickLinks) {
            is Result.Loading -> addLoading()
            is Result.Success -> {
                twoRowCarousel {
                    id("quicklink_carousel")
                    backgroundRes(R.color.white)
                    paddingDp(10)
                    withModelsFromIndexed(homeState!!.listQuickLinks!!.data!!) { i, item ->
                        QuickLinkBindingModel_().id("quicklink_$i")
                            .quickLink(item)
                            .callback(this@HomeController)
                    }
                }
            }
            else -> {//Do nothing
            }
        }

    }

    private fun buildFlashDeal() {
        when (homeState?.listFlashDeal) {
            is Result.Loading -> addLoading()
            is Result.Success -> {
                addDivider()
                sectionHeader { id("flashdeal_header") }
                carousel {
                    id("flashdeal_carousel")
                    paddingDp(5)
                    numViewsToShowOnScreen(3f)
                    onBind { _, view, _ ->
                        view.setBackgroundResource(R.color.white)
                    }
                    withModelsFromIndexed(homeState!!.listFlashDeal!!.data!!) { i, item ->
                        FlashDealBindingModel_().id("flashdeal_$i")
                            .flashDeal(item)
                            .callback(this@HomeController)
                    }
                }
                addDivider()
            }
            else -> {//Do nothing
            }
        }

    }

    private fun buildCategories() {
        when (homeState?.listCategories) {
            is Result.Loading -> addLoading()
            is Result.Success -> {
                sectionHeader {
                    id("category_header")
                    title("Danh Mục Nổi Bật")
                }
                twoRowCarousel {
                    id("category_carousel")
                    paddingDp(5)
                    numViewsToShowOnScreen(4f)
                    backgroundRes(R.color.white)

                    withModelsFromIndexed(homeState!!.listCategories!!.data!!) { i, item ->
                        CategoryBindingModel_().id("category_$i")
                            .category(item)
                            .callback(this@HomeController)
                    }
                }
                addDivider()
            }
            else -> {//Do nothing
            }
        }
    }

    private fun buildProducts() {
        when (homeState?.listProducts) {
            is Result.Loading -> addLoading()
            is Result.Success -> {
                twoRowGrid {
                    id("product_grid")
                    backgroundRes(R.color.white)
                    withModelsFromIndexed(homeState!!.listProducts!!.data!!) { i, item ->
                        ProductBindingModel_()
                            .id("product_$i")
                            .product(item)
                            .callback(this@HomeController)
                    }
                }
            }
            else -> {
            }
        }
    }


    private fun addDivider() {
        divider {
            id("divider")
            heightDp(10)
            backgroundRes(R.color.light_gray)
        }
    }

    private fun addLoading() {
        loading {
            id("loading")
        }
    }

}

