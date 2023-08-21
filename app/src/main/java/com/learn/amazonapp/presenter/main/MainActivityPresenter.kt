package com.learn.amazonapp.presenter.main


import android.util.Log
import androidx.fragment.app.Fragment
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.ListOfItemResponse
import com.learn.amazonapp.model.remote.entity.LogoutResponse
import com.learn.amazonapp.presenter.ListOfItem.ShowItemFragmentContract
import com.learn.amazonapp.presenter.ListOfItem.ShowItemPresenter
import com.learn.amazonapp.presenter.LoginFragmentPresenter
import com.learn.amazonapp.presenter.util.SharedPrefConstant
import com.learn.amazonapp.view.fragment.CartFragment
import com.learn.amazonapp.view.fragment.HomeFragment
import com.learn.amazonapp.view.fragment.SubCatFragment
import com.learn.amazonapp.view.fragment.checkout.CheckoutFragment
import com.learn.amazonapp.view.fragment.checkout.OrderConfirmFragment

class MainActivityPresenter(
    private val volleyHandler: VolleyHandler,
    private val mainActivityView: MainActivityContract.IMainActivityView)
    :MainActivityContract.IMainActivityPresenter {
    override fun decideTitleBasedOnFragment(fragment: Fragment?) {
        when(fragment){
            is HomeFragment -> mainActivityView.setTitle(HOME_TITLE)
            is SubCatFragment -> mainActivityView.setTitle(fragment.category.category_name)
            is CartFragment -> mainActivityView.setTitle(CART_TITLE)
            is CheckoutFragment->mainActivityView.setTitle(CHECKOUT_TITLE)
            is OrderConfirmFragment->mainActivityView.setTitle(ORDER_CONFIRM)
            else ->mainActivityView.setTitle(HOME_TITLE)
        }
    }

    override fun setNameAndEmail() {
        val securedSharedPreferences = SharedPrefConstant.getSecuredSharedPref(mainActivityView.context)
        val email = securedSharedPreferences.getString(SharedPrefConstant.EMAIL_ID,"") ?:""
        val name = securedSharedPreferences.getString(SharedPrefConstant.FULL_NAME,"") ?:""
        mainActivityView.setNameAndEmail(name,email)
    }

    override fun logout() {
        val securedSharedPreferences = SharedPrefConstant.getSecuredSharedPref(mainActivityView.context)
        val email = securedSharedPreferences.getString(SharedPrefConstant.EMAIL_ID,"") ?:""

        volleyHandler.postLogout(email,responseCallBack = object : ResponseCallBack {
            override fun success(getResponse: Any) {
                getResponse as LogoutResponse
                Log.i(TAG,getResponse.toString())
                mainActivityView.logout()
                val securedEditor = securedSharedPreferences.edit()
                securedEditor.clear()
                securedEditor.apply()
            }
            override fun failure(error: String) {
                Log.i(TAG,error)
                mainActivityView.logout()
                val securedEditor = securedSharedPreferences.edit()
                securedEditor.clear()
                securedEditor.apply()
            }
        })
    }

    companion object{
        const val TAG="MainActivityPresenter"
        const val HOME_TITLE="Amazon App"
        const val CART_TITLE="CART"
        const val CHECKOUT_TITLE="CHECKOUT"
        const val ORDER_CONFIRM="ORDER CONFIRMED"
    }
}