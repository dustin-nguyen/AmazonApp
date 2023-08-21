package com.learn.amazonapp.presenter.checkout

class SummaryPresenter(
    val checkoutPresenter: CheckoutPresenter,
    val summaryView: SummaryContract.ISummaryView)
    : SummaryContract.ISummaryPresenter {

    override fun setupView() {
        summaryView.setDelivery(checkoutPresenter.address)
        summaryView.setPayMethod(checkoutPresenter.paymentMethod)
        summaryView.setTotalPrice(checkoutPresenter.totalPrice)
        summaryView.getListOfItemSuccess(checkoutPresenter.listOfProductInCart)
    }
}