package com.learn.amazonapp.model.remote.entity

data class ListOfItemResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)