package com.learn.amazonapp.presenter.checkout

import com.learn.amazonapp.presenter.cart.CartContract
import com.learn.amazonapp.presenter.cart.CartPresenter

class CartCheckoutPresenter(
    var cartCheckoutFragmentView: CartCheckoutContract.ICartCheckoutFragmentView)
    :CartCheckoutContract.ICartCheckoutPresenter {
    private val cartPresenter= CartPresenter()
    override fun getAllItemInCart() {
        cartCheckoutFragmentView.getListOfItemSuccess(cartPresenter.getAllInCart())

    }

    override fun getTotalPrice(): Int {
        return cartPresenter.getTotalPrice()
    }

    override fun setTotalPrice() {
        cartCheckoutFragmentView.getTotalPriceSuccess(getTotalPrice())
    }


}