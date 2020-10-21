package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.EpoxyController
import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.base.data
import com.ttmagic.tiki.model.Banner
import com.ttmagic.tiki.model.FlashDeal
import com.ttmagic.tiki.model.QuickLink
import com.ttmagic.tiki.ui.autoScrollCarousel
import com.ttmagic.tiki.ui.twoRowCarousel
import com.ttmagic.tiki.withModelsFromIndexed
import javax.inject.Inject

class HomeController @Inject constructor() : EpoxyController() {

    private var banners: Result<List<Banner>>? = null

    private var quickLinks: Result<List<QuickLink>>? = null

    fun setData(
        banners: Result<List<Banner>>?,
        quickLinks: Result<List<QuickLink>>?,
        flashDeals: Result<List<FlashDeal>>?
    ) {
        this.banners = banners
        this.quickLinks = quickLinks
        requestModelBuild()
    }

    override fun buildModels() {
        header {
            id("header")
        }
        buildBanners()
        buildQuickLinks()
    }

    private fun buildBanners() {
        if (banners?.data.isNullOrEmpty()) return

        autoScrollCarousel {
            id("banner_carousel")
            withModelsFromIndexed(banners!!.data!!) { i, item ->
                BannerModel_()
                    .id("banner_$i")
                    .banner(item)
            }
        }
    }

    private fun buildQuickLinks() {
        if (quickLinks?.data.isNullOrEmpty()) return
        twoRowCarousel {
            paddingDp(10)
            id("quicklink_carousel")
            withModelsFromIndexed(quickLinks!!.data!!) { i, item ->
                QuickLinkItemModel_().id("quicklink_$i")
                    .quickLink(item)
            }
        }
    }


}

