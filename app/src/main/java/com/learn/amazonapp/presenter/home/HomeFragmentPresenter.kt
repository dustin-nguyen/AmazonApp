package com.learn.amazonapp.presenter.home

import android.util.Log
import com.learn.amazonapp.R
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.Category
import com.learn.amazonapp.model.remote.entity.CategoryResponse
import com.learn.amazonapp.model.remote.entity.LoginResponse
import com.learn.amazonapp.presenter.LoginFragmentPresenter


class HomeFragmentPresenter(
    private val volleyHandler: VolleyHandler,
    private val homeFragmentView: HomeFragmentContract.IHomeFragmentView)
    :HomeFragmentContract.IHomeFragmentPresenter {

    override fun getListOfCategory() {
        volleyHandler.getCategory(responseCallBack = object : ResponseCallBack {
            override fun success(getResponse: Any) {
                getResponse as CategoryResponse
                Log.i(TAG,getResponse.toString())
                homeFragmentView.getListOfCategorySuccess(getResponse.categories)
            }
            override fun failure(error: String) {
                Log.i(LoginFragmentPresenter.TAG,error)
                homeFragmentView.getListOfCategoryFail(error)
            }
        })
    }

    companion object{
        const val TAG="HomeFragmentPresenter"
        const val RESPONSE_OKAY=1
    }
}