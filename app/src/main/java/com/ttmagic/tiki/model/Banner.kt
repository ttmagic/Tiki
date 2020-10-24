package com.ttmagic.tiki.model

data class BannerResponse(val data: List<Banner>)

data class Banner(
    val image_url: String = "",
    val url: String = ""
)