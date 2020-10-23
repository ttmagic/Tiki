package com.ttmagic.tiki.ui.home.epoxy

import com.airbnb.epoxy.EpoxyController
import com.ttmagic.tiki.R
import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.base.data
import com.ttmagic.tiki.model.*
import com.ttmagic.tiki.ui.common.*
import javax.inject.Inject

class HomeController @Inject constructor() : EpoxyController() {

    private var banners: Result<List<Banner>>? = null
    private var quickLinks: Result<List<QuickLink>>? = null
    private var flashDeals: Result<List<FlashDeal>>? = null
    private var categories: Result<List<Category>>? = null
    private var products: Result<List<Product>>? = null

    var onBannerClick: ((Banner) -> Unit)? = null
    var onQuickLinkClick: ((QuickLink) -> Unit)? = null
    var onFlashDealClick: ((FlashDeal) -> Unit)? = null
    var onCategoryClick: ((Category) -> Unit)? = null
    var onProductClick: ((Product) -> Unit)? = null

    fun setData(
        banners: Result<List<Banner>>?,
        quickLinks: Result<List<QuickLink>>?,
        flashDeals: Result<List<FlashDeal>>?,
        categories: Result<List<Category>>?,
        products: Result<List<Product>>?
    ) {
        this.banners = banners
        this.quickLinks = quickLinks
        this.flashDeals = flashDeals
        this.categories = categories
        this.products = products
        requestModelBuild()
    }

    override fun buildModels() {
        buildBanners()
        buildQuickLinks()
        buildFlashDeal()
        buildCategories()
        buildProducts()
    }

    private fun buildBanners() {
        when (banners) {
            is Result.Success -> {
                autoScrollCarousel {
                    id("banner_carousel")
                    withModelsFromIndexed(banners!!.data!!) { i, item ->
                        BannerModel_()
                            .id("banner_$i")
                            .banner(item)
                            .onItemClick { onBannerClick?.invoke(it) }
                    }
                }
            }
            else -> {
                carousel {
                    id("banner_carousel")
                    withModelsFrom(arrayListOf(Banner())) {
                        BannerModel_()
                            .id("banner_loading")
                            .banner(Banner())
                            .onItemClick { onBannerClick?.invoke(it) }
                    }
                }
            }
        }
    }

    private fun buildQuickLinks() {
        when (quickLinks) {
            is Result.Loading -> addLoading()
            is Result.Success -> {
                twoRowCarousel {
                    id("quicklink_carousel")
                    backgroundRes(R.color.white)
                    paddingDp(10)
                    withModelsFromIndexed(quickLinks!!.data!!) { i, item ->
                        QuickLinkModel_().id("quicklink_$i")
                            .quickLink(item)
                            .onItemClick { onQuickLinkClick?.invoke(it) }
                    }
                }
            }
            else -> {//Do nothing
            }
        }

    }

    private fun buildFlashDeal() {
        when (flashDeals) {
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
                    withModelsFromIndexed(flashDeals!!.data!!) { i, item ->
                        FlashDealModel_().id("flashdeal_$i")
                            .flashDeal(item)
                            .onItemClick { onFlashDealClick?.invoke(it) }
                    }
                }
                addDivider()
            }
            else -> {//Do nothing
            }
        }

    }

    private fun buildCategories() {
        when (categories) {
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

                    withModelsFromIndexed(categories!!.data!!) { i, item ->
                        CategoryModel_().id("category_$i")
                            .category(item)
                            .onItemClick { onCategoryClick?.invoke(it) }
                    }
                }
                addDivider()
            }
            else -> {//Do nothing
            }
        }
    }

    private fun buildProducts() {
        when (products) {
            is Result.Loading -> addLoading()
            is Result.Success -> {
                twoRowGrid {
                    id("product_grid")
                    backgroundRes(R.color.white)
                    withModelsFromIndexed(products!!.data!!) { i, item ->
                        ProductModel_()
                            .id("product_$i")
                            .product(item)
                            .onItemClick { onProductClick?.invoke(it) }
                    }
                }
            }
            else -> {
            }
        }
    }


    private fun addDivider() {
        epoxyDivider {
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

