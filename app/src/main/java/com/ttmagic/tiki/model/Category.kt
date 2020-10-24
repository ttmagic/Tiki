package com.ttmagic.tiki.model

data class CategoryResponse(val data: List<Category>)

data class Category(
    val id: Int,
    val include_in_menu: Boolean,
    val is_leaf: Boolean,
    val level: Int,
    val meta_description: String,
    val meta_keywords: Any,
    val meta_title: String,
    val name: String,
    val parent_id: Int,
    val product_count: Int,
    val status: String,
    val thumbnail_url: String,
    val type: String,
    val url_key: String
)