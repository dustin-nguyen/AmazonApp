package com.learn.amazonapp.presenter

interface MVP {
    interface IItemPresenter{
        fun getItem()
        fun addItem()
    }
    interface ItemView{
        fun setResult()
        fun setError(error: String)
        fun makeToast(message: String)
    }
}