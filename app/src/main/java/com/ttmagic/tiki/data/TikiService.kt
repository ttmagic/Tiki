package com.ttmagic.tiki.data

import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.model.*
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TikiService {

    @GET("v2/home/banners/v2")
    suspend fun getBanners(): Result<BannerResponse>

    @GET("shopping/v2/widgets/quick_link")
    suspend fun getQuickLink(): Result<QuickLinkResponse>

    @GET("v2/widget/deals/hot")
    suspend fun getFlashDeal(): Result<FlashDealResponse>

    @GET("v2/categories?parent_id=2")
    suspend fun getCategories(): Result<CategoryResponse>

    @GET("personalization/v2/products")
    suspend fun getProducts(@QueryMap query: Map<String, String>): Result<ProductResponse>
}