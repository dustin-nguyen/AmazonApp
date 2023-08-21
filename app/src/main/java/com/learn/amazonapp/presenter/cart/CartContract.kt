package com.learn.amazonapp.presenter.cart

import android.content.Context
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.entity.Product

interface CartContract {

    interface ICartPresenter{

        fun getAllItemInCart()

        fun getAllInCart():ArrayList<ProductInCart>

        fun setFragmentView(view: ICartFragmentView)

        fun updateTotalPrice(price: Int)

        fun updateAllItemInCart(listOfProduct: ArrayList<ProductInCart>)

        fun getTotalPrice(): Int
    }

    interface ICartFragmentView{

        var fragmentContext: Context

        fun getListOfItemSuccess(listOfProduct: List<ProductInCart>)

        fun getListOfItemCategoryFail(error: String)

        fun updateTotalPrice(price: Int)

        fun getTotalPrice(): Int
    }
}