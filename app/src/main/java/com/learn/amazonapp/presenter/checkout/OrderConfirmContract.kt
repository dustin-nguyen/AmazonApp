package com.learn.amazonapp.presenter.checkout

import android.content.Context
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.entity.Address
import com.learn.amazonapp.model.remote.entity.PlaceOrderResponse
import java.lang.Error

interface OrderConfirmContract {
    interface IOrderConfirmPresenter{

        fun setupView(placeOrderResponse: PlaceOrderResponse)
        fun placeOrder()

    }
    interface IOrderConfirmView{

        var fragmentContext: Context
        fun setTotalPrice(totalPrice: Int)
        fun getListOfItemSuccess(listOfProduct: List<ProductInCart>)

        fun setPayMethod(payment: String)
        fun setDelivery(delivery: Address)
        fun setOrderIDAndStatus(orderId:String, status:String)

        fun placeOrderFail(error: String)

    }
}