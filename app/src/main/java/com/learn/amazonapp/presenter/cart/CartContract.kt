package com.learn.amazonapp.presenter.cart

import android.content.Context
import com.learn.amazonapp.model.remote.entity.Product

interface CartContract {
    interface ICartPresenter{
        fun getAllItemInCart()
        fun setFragmentView(view: ICartFragmentView)
        fun updateTotalPrice(price:Int)
    }
    interface ICartFragmentView{
        var fragmentContext: Context
        fun getListOfItemSuccess(listOfProduct: List<Product>)
        fun getListOfItemCategoryFail(error: String)
        fun updateTotalPrice(price:Int)
        fun getTotalPrice():Int

    }
}