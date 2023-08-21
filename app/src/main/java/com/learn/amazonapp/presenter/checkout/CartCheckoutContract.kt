package com.learn.amazonapp.presenter.checkout

import android.content.Context
import com.learn.amazonapp.model.ProductInCart

interface CartCheckoutContract {

    interface ICartCheckoutPresenter{

        fun getAllItemInCart()

        fun getTotalPrice(): Int

        fun setTotalPrice()
    }

    interface ICartCheckoutFragmentView{

        var fragmentContext: Context

        fun getListOfItemSuccess(listOfProduct: ArrayList<ProductInCart>)

        fun getListOfItemCategoryFail(error: String)

        fun getTotalPriceSuccess(totalPrice:Int)
    }
}