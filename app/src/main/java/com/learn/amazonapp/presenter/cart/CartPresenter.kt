package com.learn.amazonapp.presenter.cart

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.learn.amazonapp.Constant.PRODUCT_KEY
import com.learn.amazonapp.Constant.TO_CART_ACTION
import com.learn.amazonapp.model.remote.entity.Product
import com.learn.amazonapp.presenter.ListOfItem.ShowItemPresenter

class CartPresenter()
    :BroadcastReceiver(), CartContract.ICartPresenter {
    private lateinit var cartFragmentView: CartContract.ICartFragmentView
    private lateinit var view: View
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action){
            TO_CART_ACTION->{
                val dataIntent =intent.extras?.getParcelable<Product>(PRODUCT_KEY)?.let {
                        pro:Product ->
                    listOfProduct.add(pro)
                    Log.i(TAG,"Item in cart :\n${listOfProduct.toString()}")

                }
            }
            else->{
            }
        }
    }
    override fun getAllItemInCart() {
        cartFragmentView.getListOfItemSuccess(listOfProduct)
    }

    override fun setFragmentView(view: CartContract.ICartFragmentView) {
        cartFragmentView=view
    }

    override fun updateTotalPrice(price: Int) {
        var currentPrice=cartFragmentView.getTotalPrice()
        cartFragmentView.updateTotalPrice(currentPrice+price)
    }


    companion object{
        var listOfProduct= ArrayList<Product>()
        const val TAG="CartPresenter"

    }


}