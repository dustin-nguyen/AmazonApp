package com.learn.amazonapp.presenter.checkout

import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.entity.Address

class CheckoutPresenter:CheckoutContract.ICheckoutPresenter {

    // Backing fields for properties
    private lateinit var _paymentMethod: String
    private lateinit var _address: Address
    private var _listOfProductInCart: List<ProductInCart> = ArrayList()
    private  var _totalPrice: Int= 0

    override var address: Address
        get() = _address
        set(value) {_address=value}
    override var listOfProductInCart: List<ProductInCart>
        get() = _listOfProductInCart
        set(value) {_listOfProductInCart=value}
    override var paymentMethod: String
        get() = _paymentMethod
        set(value) {_paymentMethod=value}
    override var totalPrice: Int
        get() = _totalPrice
        set(value) {_totalPrice=value}


}