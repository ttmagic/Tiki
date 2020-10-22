package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.EpoxyController
import com.ttmagic.tiki.R
import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.base.data
import com.ttmagic.tiki.model.Banner
import com.ttmagic.tiki.model.Category
import com.ttmagic.tiki.model.FlashDeal
import com.ttmagic.tiki.model.QuickLink
import com.ttmagic.tiki.ui.common.*
import javax.inject.Inject

class HomeController @Inject constructor() : EpoxyController() {

    private var banners: Result<List<Banner>>? = null
    private var quickLinks: Result<List<QuickLink>>? = null
    private var flashDeals: Result<List<FlashDeal>>? = null
    private var categories: Result<List<Category>>? = null

    var onBannerClick: ((Banner) -> Unit)? = null
    var onQuickLinkClick: ((QuickLink) -> Unit)? = null
    var onFlashDealClick: ((FlashDeal) -> Unit)? = null
    var onCategoryClick: ((Category) -> Unit)? = null

    fun setData(
        banners: Result<List<Banner>>?,
        quickLinks: Result<List<QuickLink>>?,
        flashDeals: Result<List<FlashDeal>>?,
        categories:Result<List<Category>>?
    ) {
        this.banners = banners
        this.quickLinks = quickLinks
        this.flashDeals = flashDeals
        this.categories = categories
        requestModelBuild()
    }

    override fun buildModels() {
        header {
            id("header")
        }
        buildBanners()
        buildQuickLinks()
        buildFlashDeal()
        buildCategories()
    }

    private fun buildBanners() {
        when (banners) {
            is Result.Loading -> {
                loading {
                    id("loading_banner")
                }
            }
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
            else -> {//Do nothing
            }
        }
    }

    private fun buildQuickLinks() {
        when (quickLinks) {
            is Result.Loading -> {
                loading {
                    id("loading_quicklink")
                }
            }
            is Result.Success -> {
                twoRowCarousel {
                    id("quicklink_carousel")
                    backgroundRes(R.color.white)
                    paddingDp(10)
                    withModelsFromIndexed(quickLinks!!.data!!) { i, item ->
                        QuickLinkItemModel_().id("quicklink_$i")
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
            is Result.Loading -> {
                loading {
                    id("loading_flashdeal")
                }
            }
            is Result.Success -> {
                addDivider()
                sectionHeader { id("flashdeal_header") }
                carousel {
                    id("flashdeal_carousel")
                    paddingDp(5)
                    numViewsToShowOnScreen(3f)
                    withModelsFromIndexed(flashDeals!!.data!!) { i, item ->
                        FlashDealItemModel_().id("flashdeal_$i")
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
            is Result.Loading -> {
                loading {
                    id("loading_category")
                }
            }
            is Result.Success -> {
                sectionHeader {
                    id("category_header")
                    title("Danh mục sản phẩm")
                }
                carousel {
                    id("category_carousel")
                    paddingDp(5)
                    numViewsToShowOnScreen(5f)

                    withModelsFromIndexed(categories!!.data!!) { i, item ->
                        CategoryItemModel_().id("category_$i")
                            .category(item)
                            .onItemClick { onCategoryClick?.invoke(it) }
                    }
                }
            }
            else -> {//Do nothing
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

}

