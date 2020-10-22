package com.ttmagic.tiki.model

data class FlashDealResponse(
    //val categories: List<Category>,
    val `data`: List<FlashDeal>
    //val tabs: List<Tab>,
    //val version: String
)

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

data class Product(
    val badges: List<Any>,
    val discount: Int,
    val id: Int,
    val inventory: Inventory,
    val is_flower: Boolean,
    val is_fresh: Boolean,
    val is_gift_card: Boolean,
    val is_visible: Boolean,
    val list_price: Int,
    val master_id: Int,
    val name: String,
    val order_count: Int,
    val price: Int,
    val price_prefix: String,
    val rating_average: Int,
    val review_count: Int,
    val seller_product_id: Int,
    val sku: Any,
    val thumbnail_url: String,
    val url_attendant_input_form: String,
    val url_path: String
)

data class Progress(
    val percent: Int,
    val progress_text: String,
    val qty: Int,
    val qty_ordered: Int,
    val qty_remain: Int,
    val status: String
)


data class Inventory(
    val fulfillment_type: String,
    val product_virtual_type: Any
)

data class Tab(
    val active: Boolean,
    val display: String,
    val from_date: String,
    val query_value: Int,
    val to_date: String
)