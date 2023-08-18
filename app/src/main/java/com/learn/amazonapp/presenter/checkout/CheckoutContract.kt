package com.learn.amazonapp.presenter.checkout

import android.content.Context
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.entity.Address

interface CheckoutContract {
    interface ICheckoutPresenter{
        var address:Address
        var listOfProductInCart: List<ProductInCart>
        var paymentMethod: String
        var totalPrice: Int

    }
    interface ICheckoutFragmentView{
        var fragmentContext: Context



    }
}