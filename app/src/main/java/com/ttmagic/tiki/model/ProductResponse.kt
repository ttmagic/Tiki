package com.ttmagic.tiki.model

data class ProductResponse(
    val `data`: List<Product>,
    val paging: Paging,
    val total: Int
)

data class Paging(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val per_page: Int,
    val to: Int,
    val total: Int
)

/*data class Product(
    val default_spid: Int,
    val discount_rate: Int,
    val id: Int,
    val inventory_status: String,
    val list_price: Int,
    val name: String,
    val price: Int,
    val rating_average: Double,
    val review_count: Int,
    val thumbnail_url: String,
    val type: String,
    val url_key: String
)*/

data class Product(
    val discount_rate: Int,
    val id: Int,
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
    val review_count: Int,
    val seller_product_id: Int,
    val sku: Any,
    val thumbnail_url: String,
    val url_attendant_input_form: String,
    val url_path: String,
    val url_key: String
)

data class ProductQuery(val page: Int, val limit: Int) {

    fun toQueryMap(): Map<String, String> {
        return hashMapOf(
            "page" to page.toString(),
            "limit" to limit.toString()
        )
    }
}