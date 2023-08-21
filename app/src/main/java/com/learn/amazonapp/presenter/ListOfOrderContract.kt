package com.learn.amazonapp.presenter

import android.content.Context
import com.learn.amazonapp.model.remote.entity.Order
import com.learn.amazonapp.model.remote.entity.Product

interface ListOfOrderContract {

    interface IListOfOrderPresenter{

        fun getListOfOrder()
    }

    interface IListOfOrderView{

        var fragmentContext: Context

        fun getListOfItemSuccess(listOfOrder: List<Order>)

        fun getListOfItemCategoryFail(error: String)
    }
}