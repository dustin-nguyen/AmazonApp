package com.learn.amazonapp.presenter.checkout

import android.content.Context
import com.learn.amazonapp.model.remote.entity.Address

interface DeliveryContract {
    interface IDeliveryPresenter{
        fun getListOfAddress( userId:String)
        fun getListOfAddressWithCurrentUser()
    }
    interface IDeliveryView{

        var fragmentContext: Context
        fun getListOfItemSuccess(listOfAddress: List<Address>)
        fun getListOfItemCategoryFail(error: String)
    }
}