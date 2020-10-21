package com.ttmagic.tiki.model

data class BannerResponse(val `data`: List<Banner>)

data class Banner(
    val content: String,
    val id: Int,
    val image_url: String,
    val mobile_url: String,
    val ratio: Double,
    val thumbnail_url: String,
    val title: String,
    val url: String
)