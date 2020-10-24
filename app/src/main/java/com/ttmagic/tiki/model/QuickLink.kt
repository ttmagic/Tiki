package com.ttmagic.tiki.model

data class QuickLinkResponse(val data: List<List<QuickLink>>)

data class QuickLink(
    val authentication: Boolean,
    val content: String,
    val image_url: String,
    val title: String,
    val url: String,
    val web_url: String
)