package com.learn.amazonapp.presenter

import android.content.Context
import com.learn.amazonapp.model.remote.entity.Product

interface CartContract {
    interface ICartPresenter{
        fun getAllItemInCart()
        fun setView(view:ICartFragmentView)
    }
    interface ICartFragmentView{
        var fragmentContext: Context
        fun getListOfItemSuccess(listOfProduct: List<Product>)
        fun getListOfItemCategoryFail(error: String)

    }
}