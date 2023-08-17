package com.learn.amazonapp.model.remote.entity

data class ListOfAddressResponse(
    val addresses: List<Address>,
    val message: String,
    val status: Int
)