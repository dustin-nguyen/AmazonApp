package com.learn.amazonapp.presenter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.material.snackbar.Snackbar
import com.learn.amazonapp.Constant.PRODUCT_KEY
import com.learn.amazonapp.Constant.TO_CART_ACTION
import com.learn.amazonapp.model.remote.entity.Product
import com.learn.amazonapp.presenter.ListOfItem.ShowItemFragmentContract

class CartPresenter()
    :BroadcastReceiver(),CartContract.ICartPresenter {
    private lateinit var cartFragmentView: CartContract.ICartFragmentView
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action){
            TO_CART_ACTION->{
                val dataIntent =intent.extras?.getParcelable<Product>(PRODUCT_KEY)?.let {
                        pro:Product ->
                    listOfProduct.add(pro)

                }
            }
            else->{
            }
        }
    }
    override fun getAllItemInCart() {
        cartFragmentView.getListOfItemSuccess(listOfProduct)
    }

    override fun setView(view: CartContract.ICartFragmentView) {
        cartFragmentView=view
    }

    companion object{
        var listOfProduct= ArrayList<Product>()

    }


}