package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.TypedEpoxyController
import com.ttmagic.tiki.*
import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.model.*
import com.ttmagic.tiki.ui.common.*
import javax.inject.Inject

class HomeController @Inject constructor() : TypedEpoxyController<HomeViewState>(), OnItemClick {

    var onBannerClick: ((Banner) -> Unit)? = null
    var onQuickLinkClick: ((QuickLink) -> Unit)? = null
    var onFlashDealClick: ((FlashDeal) -> Unit)? = null
    var onCategoryClick: ((Category) -> Unit)? = null
    var onProductClick: ((Product) -> Unit)? = null

    override fun buildModels(data: HomeViewState?) {
        data?.run {
            listBanners?.let { buildBanners(it) }
            listQuickLinks?.let { buildQuickLinks(it) }
            listFlashDeal?.let { buildFlashDeal(it) }
            listCategories?.let { buildCategories(it) }
            listProducts?.let { buildProducts(it) }
        }
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

    private fun buildBanners(banners: Result<List<Banner>>) {
        when (banners) {
            is Result.Success -> {
                autoScrollCarousel {
                    id("banner_carousel")
                    mapIndexed(banners.data) { i, item ->
                        BannerBindingModel_()
                            .id(i)
                            .banner(item)
                            .callback(this@HomeController)
                    }
                }
            }
            is Result.Loading -> {
                carousel {
                    id("banner_carousel")
                    map(arrayListOf(Banner())) {
                        BannerBindingModel_()
                            .id("banner_loading")
                            .banner(Banner())
                            .callback(this@HomeController)
                    }
                }
            }
            else -> {   // Do nothing
            }
        }
    }

    private fun buildQuickLinks(quickLinks: Result<List<QuickLink>>) {
        when (quickLinks) {
            is Result.Loading -> loading()
            is Result.Success -> {
                twoRowCarousel {
                    id("quicklink_carousel")
                    paddingDp(10)
                    mapIndexed(quickLinks.data) { i, item ->
                        QuickLinkBindingModel_()
                            .id(i)
                            .quickLink(item)
                            .callback(this@HomeController)
                    }
                }
                divider()
            }
            else -> {   // Do nothing
            }
        }

    }

    private fun buildFlashDeal(flashDeals: Result<List<FlashDeal>>) {
        when (flashDeals) {
            is Result.Loading -> loading()
            is Result.Success -> {
                sectionHeader { id("flashdeal_header") }
                carousel {
                    id("flashdeal_carousel")
                    paddingDp(5)
                    numViewsToShowOnScreen(3f)
                    onBind { _, view, _ ->
                        view.setBackgroundResource(R.color.white)
                    }
                    mapIndexed(flashDeals.data) { i, item ->
                        FlashDealBindingModel_()
                            .id(i)
                            .flashDeal(item)
                            .callback(this@HomeController)
                    }
                }
                divider()
            }
            else -> {   // Do nothing
            }
        }
    }

    private fun buildCategories(categories: Result<List<Category>>) {
        when (categories) {
            is Result.Loading -> loading()
            is Result.Success -> {
                sectionHeader {
                    id("category_header")
                    title("Danh Mục Nổi Bật")
                }
                twoRowCarousel {
                    id("category_carousel")
                    paddingDp(5)
                    numViewsToShowOnScreen(4f)
                    mapIndexed(categories.data) { i, item ->
                        CategoryBindingModel_()
                            .id(i)
                            .category(item)
                            .callback(this@HomeController)
                    }
                }
                divider()
            }
            else -> {   // Do nothing
            }
        }
    }

    private fun buildProducts(products: Result<List<Product>>) {
        when (products) {
            is Result.Loading -> loading()
            is Result.Success -> {
                twoRowGrid {
                    id("product_grid")
                    mapIndexed(products.data) { i, item ->
                        ProductBindingModel_()
                            .id(i)
                            .product(item)
                            .callback(this@HomeController)
                    }
                }
            }
            else -> {   // Do nothing
            }
        }
    }

}

