package com.learn.amazonapp.presenter.ListOfItem

import android.util.Log
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.CategoryResponse
import com.learn.amazonapp.model.remote.entity.ListOfItemResponse
import com.learn.amazonapp.presenter.LoginFragmentPresenter
import com.learn.amazonapp.presenter.home.HomeFragmentContract

class ShowItemPresenter (
    private val volleyHandler: VolleyHandler,
    private val showItemFragmentView: ShowItemFragmentContract.IShowItemFragmentView)
    : ShowItemFragmentContract.IShowItemFragmentPresenter {

    override fun getListOfItem(subCategoryId: String) {
        volleyHandler.getItemBySubCatId(subCategoryId,responseCallBack = object : ResponseCallBack {
            override fun success(getResponse: Any) {
                getResponse as ListOfItemResponse
                Log.i(TAG,getResponse.toString())
                showItemFragmentView.getListOfItemSuccess(getResponse.products)
            }
            override fun failure(error: String) {
                Log.i(LoginFragmentPresenter.TAG,error)
                showItemFragmentView.getListOfItemCategoryFail(error)
            }
        })
    }

    companion object{
        const val TAG="ShowItemPresenter"
    }
}