package com.learn.amazonapp.model.remote.entity

data class OrderResponse(
    val message: String,
    val orders: List<Order>,
    val status: Int
)