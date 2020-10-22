package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.EpoxyController
import com.ttmagic.tiki.R
import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.base.data
import com.ttmagic.tiki.model.Banner
import com.ttmagic.tiki.model.FlashDeal
import com.ttmagic.tiki.model.QuickLink
import com.ttmagic.tiki.ui.common.*
import javax.inject.Inject

class HomeController @Inject constructor() : EpoxyController() {

    private var banners: Result<List<Banner>>? = null
    private var quickLinks: Result<List<QuickLink>>? = null
    private var flashDeals: Result<List<FlashDeal>>? = null

    var onBannerClick: ((Banner) -> Unit)? = null
    var onQuickLinkClick: ((QuickLink) -> Unit)? = null
    var onFlashDealClick: ((FlashDeal) -> Unit)? = null

    fun setData(
        banners: Result<List<Banner>>?,
        quickLinks: Result<List<QuickLink>>?,
        flashDeals: Result<List<FlashDeal>>?
    ) {
        this.banners = banners
        this.quickLinks = quickLinks
        this.flashDeals = flashDeals
        requestModelBuild()
    }

    override fun buildModels() {
        header {
            id("header")
        }
        buildBanners()
        buildQuickLinks()
        buildFlashDeal()
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
        if (flashDeals == null) return
        addDivider()
        flashDealSectionHeader { id("flashdeal_header") }
        when (flashDeals) {
            is Result.Loading -> {
                loading {
                    id("loading_flashdeal")
                }
            }
            is Result.Success -> {
                carousel {
                    id("flashdeal_carousel")
                    paddingDp(10)
                    numViewsToShowOnScreen(3f)
                    withModelsFromIndexed(flashDeals!!.data!!) { i, item ->
                        FlashDealItemModel_().id("flashdeal_$i")
                            .flashDeal(item)
                            .onItemClick { onFlashDealClick?.invoke(it) }
                    }
                }
            }
            else -> {//Do nothing
            }
        }
        addDivider()
    }


    private fun addDivider() {
        epoxyDivider {
            id("divider")
            heightDp(10)
            backgroundRes(R.color.light_gray)
        }
    }

}

