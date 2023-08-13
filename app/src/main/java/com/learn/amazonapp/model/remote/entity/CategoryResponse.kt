package com.learn.amazonapp.model.remote.entity

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)