package com.learn.amazonapp.model.remote.entity

data class ProductResponse(
    val message: String,
    val product: ProductX,
    val status: Int
)