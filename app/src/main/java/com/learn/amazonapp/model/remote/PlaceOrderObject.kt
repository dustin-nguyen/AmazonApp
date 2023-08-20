package com.learn.amazonapp.model.remote

import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.entity.Address


data class PlaceOrderObject(
    val listOfProduct:List<ProductInCart>,
    val address: Address,
    val payMethod:String,
    val userid: String,
    val billAmount:Int)
