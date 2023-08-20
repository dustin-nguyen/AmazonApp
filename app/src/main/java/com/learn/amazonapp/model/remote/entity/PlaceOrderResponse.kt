package com.learn.amazonapp.model.remote.entity

data class PlaceOrderResponse(
    val message: String,
    val order_id: Int?=-1,
    val status: Int
)