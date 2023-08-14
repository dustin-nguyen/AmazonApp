package com.learn.amazonapp.model.remote.entity

data class Category(
    val category_id: String,
    var category_image_url: String,
    val category_name: String,
    val is_active: String
)