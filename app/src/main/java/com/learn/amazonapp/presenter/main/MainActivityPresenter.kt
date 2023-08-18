package com.learn.amazonapp.presenter.main

import androidx.fragment.app.Fragment
import com.learn.amazonapp.presenter.ListOfItem.ShowItemFragmentContract
import com.learn.amazonapp.view.fragment.CartFragment
import com.learn.amazonapp.view.fragment.HomeFragment
import com.learn.amazonapp.view.fragment.SubCatFragment
import com.learn.amazonapp.view.fragment.checkout.CheckoutFragment

class MainActivityPresenter( private val mainActivityView: MainActivityContract.IMainActivityView)
    :MainActivityContract.IMainActivityPresenter {
    override fun decideTitleBasedOnFragment(fragment: Fragment?) {

        when(fragment){
            is HomeFragment -> mainActivityView.setTitle(HOME_TITLE)
            is SubCatFragment -> mainActivityView.setTitle(fragment.category.category_name)
            is CartFragment -> mainActivityView.setTitle(CART_TITLE)
            is CheckoutFragment->mainActivityView.setTitle(CHECKOUT_TITLE)
            else ->mainActivityView.setTitle(HOME_TITLE)
        }
    }
    companion object{
        const val HOME_TITLE="Amazon App"
        const val CART_TITLE="CART"
        const val CHECKOUT_TITLE="CHECKOUT"
    }
}