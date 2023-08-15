package com.learn.amazonapp.presenter.ListOfItem

import android.content.Context
import com.learn.amazonapp.model.remote.entity.Product
import com.learn.amazonapp.model.remote.entity.Subcategory

interface ShowItemFragmentContract {
    interface IShowItemFragmentPresenter{
        fun getListOfItem( subCategoryId:String)
    }
    interface IShowItemFragmentView{
        var fragmentContext: Context
        fun getListOfItemSuccess(listOfProduct: List<Product>)
        fun getListOfItemCategoryFail(error: String)


    }
}