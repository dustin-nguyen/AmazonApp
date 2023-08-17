package com.learn.amazonapp.view

import androidx.fragment.app.Fragment
import com.learn.amazonapp.model.remote.entity.Product

interface HomeCommunicator {
    fun goToFragment(backStackEntryName:String,fragment: Fragment)
    fun sendProductToCart(product: Product)

}