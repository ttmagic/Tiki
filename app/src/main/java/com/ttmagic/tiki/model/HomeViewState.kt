package com.ttmagic.tiki.model

import com.ttmagic.tiki.base.Result

data class HomeViewState(
    val listBanners: Result<List<Banner>>? = null,
    val listQuickLinks: Result<List<QuickLink>>? = null,
    val listFlashDeal: Result<List<FlashDeal>>? = null,
    val listCategories: Result<List<Category>>? = null,
    val listProducts: Result<List<Product>>? = null
)