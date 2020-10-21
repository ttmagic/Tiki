package com.ttmagic.tiki.data

import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.model.BannerResponse
import com.ttmagic.tiki.model.FlashDealResponse
import com.ttmagic.tiki.model.QuickLinkResponse
import retrofit2.http.GET

interface TikiService {

    @GET("v2/home/banners/v2")
    suspend fun getBanners(): Result<BannerResponse>

    @GET("shopping/v2/widgets/quick_link")
    suspend fun getQuickLink(): Result<QuickLinkResponse>

    @GET("v2/widget/deals/hot")
    suspend fun getFlashDeal(): Result<FlashDealResponse>
}