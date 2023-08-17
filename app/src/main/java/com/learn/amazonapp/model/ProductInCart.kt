package com.learn.amazonapp.model

import com.learn.amazonapp.model.remote.entity.Product

data class ProductInCart(
    val product: Product,
    var quantity:Int,
    var amount:Int) {
}