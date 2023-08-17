package com.learn.amazonapp.view

import com.learn.amazonapp.model.ProductInCart

interface CartCommunicator {
    fun updateList(product:ProductInCart)
}