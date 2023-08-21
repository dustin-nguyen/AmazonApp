package com.learn.amazonapp.presenter

import android.util.Log
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.AddAddressResponse
import com.learn.amazonapp.model.remote.entity.ListOfItemResponse
import com.learn.amazonapp.model.remote.entity.OrderResponse
import com.learn.amazonapp.presenter.ListOfItem.ShowItemFragmentContract
import com.learn.amazonapp.presenter.checkout.DeliveryPresenter
import com.learn.amazonapp.presenter.util.SharedPrefConstant

class ListOfOrderPresenter(
    private val volleyHandler: VolleyHandler,
    private val listOfOrderView: ListOfOrderContract.IListOfOrderView)
    : ListOfOrderContract.IListOfOrderPresenter {

    override fun getListOfOrder() {
        val securedSharedPreferences = SharedPrefConstant.getSecuredSharedPref(listOfOrderView.fragmentContext)
        val userId = securedSharedPreferences.getString(SharedPrefConstant.USER_ID,"") ?:""

        volleyHandler.getOrders(userId,responseCallBack = object : ResponseCallBack {
            override fun success(getResponse: Any) {
                getResponse as OrderResponse
                Log.i(TAG,getResponse.toString())
                listOfOrderView.getListOfItemSuccess(getResponse.orders)
            }
            override fun failure(error: String) {
                Log.i(TAG,error)
                listOfOrderView.getListOfItemCategoryFail(error)
            }
        })
    }

    companion object {
        const val TAG = "ListOfOrderPresenter"
    }
}