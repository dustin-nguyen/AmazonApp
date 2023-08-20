package com.learn.amazonapp.presenter.checkout

import android.util.Log
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.AddAddressResponse
import com.learn.amazonapp.model.remote.entity.Address
import com.learn.amazonapp.model.remote.entity.ListOfAddressResponse
import com.learn.amazonapp.model.remote.entity.ListOfItemResponse
import com.learn.amazonapp.presenter.ListOfItem.ShowItemFragmentContract
import com.learn.amazonapp.presenter.ListOfItem.ShowItemPresenter
import com.learn.amazonapp.presenter.LoginFragmentPresenter
import com.learn.amazonapp.presenter.util.SharedPrefConstant
import com.learn.amazonapp.view.fragment.checkout.DeliveryFragment

class DeliveryPresenter(private val volleyHandler: VolleyHandler,
                        private val deliveryFragment: DeliveryContract.IDeliveryView):DeliveryContract.IDeliveryPresenter {
    override fun getListOfAddress(userId: String) {
        volleyHandler.getListOfAddressOfUser(userId,responseCallBack = object : ResponseCallBack {
            override fun success(getResponse: Any) {
                getResponse as ListOfAddressResponse
                Log.i(TAG,getResponse.toString())
                deliveryFragment.getListOfItemSuccess(getResponse.addresses)

            }

            override fun failure(error: String) {
                Log.i(LoginFragmentPresenter.TAG,error)
                deliveryFragment.getListOfItemCategoryFail(error)
            }
        })
    }
    override fun getListOfAddressWithCurrentUser() {
        val securedSharedPreferences = SharedPrefConstant.getSecuredSharedPref(deliveryFragment.fragmentContext)
        val userId = securedSharedPreferences.getString(SharedPrefConstant.USER_ID,"") ?:""

        if(userId != "")
            getListOfAddress(userId)
        else
            Log.e(TAG,"userId $userId")
    }

    override fun addAddress(address: Address) {
        val securedSharedPreferences = SharedPrefConstant.getSecuredSharedPref(deliveryFragment.fragmentContext)
        val userId = securedSharedPreferences.getString(SharedPrefConstant.USER_ID,"") ?:""

        volleyHandler.addAddressForUser(userId,address,responseCallBack = object : ResponseCallBack {
            override fun success(getResponse: Any) {
                getResponse as AddAddressResponse
                Log.i(TAG,getResponse.toString())
                deliveryFragment.addAddressSuccess()

            }

            override fun failure(error: String) {
                Log.i(TAG,error)
                deliveryFragment.addAddressFail(error)
            }
        })
    }

    companion object{
        const val TAG="DeliveryPresenter"

    }
}