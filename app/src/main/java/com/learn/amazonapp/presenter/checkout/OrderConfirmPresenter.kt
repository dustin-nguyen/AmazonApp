package com.learn.amazonapp.presenter.checkout

import android.util.Log
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.PlaceOrderObject
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.AddAddressResponse
import com.learn.amazonapp.model.remote.entity.PlaceOrderResponse
import com.learn.amazonapp.presenter.util.SharedPrefConstant

class OrderConfirmPresenter(
    val volleyHandler: VolleyHandler,
    val checkoutPresenter: CheckoutPresenter,
    val orderConfirmView: OrderConfirmContract.IOrderConfirmView)
    : OrderConfirmContract.IOrderConfirmPresenter {

    lateinit var placeOrderObject: PlaceOrderObject
    override fun setupView(placeOrderResponse: PlaceOrderResponse) {
        orderConfirmView.setOrderIDAndStatus(
            placeOrderResponse.order_id.toString(),
           "Confirm")

        if(::placeOrderObject.isInitialized ==true){
            orderConfirmView.setDelivery(placeOrderObject.address)
            orderConfirmView.setPayMethod(placeOrderObject.payMethod)
            orderConfirmView.setTotalPrice(placeOrderObject.billAmount)
            orderConfirmView.getListOfItemSuccess(placeOrderObject.listOfProduct)
        }else{
            orderConfirmView.placeOrderFail("placeOrderObject is not initialized")
        }
    }

    override fun placeOrder() {
        val securedSharedPreferences = SharedPrefConstant.getSecuredSharedPref(orderConfirmView.fragmentContext)
        val userId = securedSharedPreferences.getString(SharedPrefConstant.USER_ID,"") ?:""

        val address= checkoutPresenter.address
        val paymethod=checkoutPresenter.paymentMethod
        val total=checkoutPresenter.totalPrice
        val listOfProductInCart=checkoutPresenter.listOfProductInCart

        placeOrderObject= PlaceOrderObject(
            address = address,
            payMethod = paymethod,
            listOfProduct = listOfProductInCart,
            billAmount = total,
            userid = userId)

        volleyHandler.postPlaceOrder(placeOrderObject,responseCallBack = object :
            ResponseCallBack {
            override fun success(getResponse: Any) {
                getResponse as PlaceOrderResponse
                Log.i(TAG,getResponse.toString())
                if (getResponse.order_id != -1)
                    setupView(getResponse)
                else
                    orderConfirmView.placeOrderFail(getResponse.message)

            }

            override fun failure(error: String) {
                Log.i(TAG,error)
                orderConfirmView.placeOrderFail(error)
            }
        })
    }
    companion object{
        const val TAG="OrderConfirmPresenter"

    }

}