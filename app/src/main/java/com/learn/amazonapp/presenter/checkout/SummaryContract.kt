package com.learn.amazonapp.presenter.checkout

import android.content.Context
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.entity.Address

interface SummaryContract {
    interface ISummaryPresenter{

        fun setupView()

    }
    interface ISummaryView{

        var fragmentContext: Context

        fun setTotalPrice(totalPrice: Int)

        fun getListOfItemSuccess(listOfProduct: List<ProductInCart>)

        fun setPayMethod(payment: String)

        fun setDelivery(delivery:Address)
    }
}