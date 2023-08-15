package com.learn.amazonapp.model.remote.entity

data class SubCatResponse(
    val message: String,
    val status: Int,
    val subcategories: List<Subcategory>
)