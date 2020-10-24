package com.ttmagic.tiki.model

data class FlashDealResponse(val data: List<FlashDeal>)

data class FlashDeal(
    val deal_id: Int,
    val deal_status: String,
    val deal_version: Int,
    val discount_percent: Int,
    val from_date: String,
    val product: Product,
    val progress: Progress,
    val special_from_date: Int,
    val special_price: Int,
    val special_to_date: Int,
    val status: Int,
    val tags: String,
    val url: String
)

data class Progress(
    val percent: Int,
    val progress_text: String,
    val qty: Int,
    val qty_ordered: Int,
    val qty_remain: Int,
    val status: String
)