package com.learn.amazonapp.presenter.subcat

import android.util.Log
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.CategoryResponse
import com.learn.amazonapp.model.remote.entity.SubCatResponse
import com.learn.amazonapp.model.remote.entity.Subcategory
import com.learn.amazonapp.presenter.LoginFragmentPresenter
import com.learn.amazonapp.presenter.home.HomeFragmentContract
import com.learn.amazonapp.presenter.home.HomeFragmentPresenter

class SubCatPresenter(
                      private val volleyHandler: VolleyHandler,
                      private val subCatFragment: SubCatFragmentContract.ISubCatFragmentView):SubCatFragmentContract.ISubCatFragmentPresenter {
    override fun getListOfSubCategory(id: String) {
        volleyHandler.getSubCategoryById(id,responseCallBack = object : ResponseCallBack {
            override fun success(getResponse: Any) {
                getResponse as SubCatResponse
                Log.i(TAG,getResponse.toString())
                subCatFragment.getListOfSubCategorySuccess(getResponse.subcategories)


            }

            override fun failure(error: String) {
                Log.i(TAG,error)
                subCatFragment.getListOfSubCategoryFail(error)
            }
        })
    }
    companion object{
        const val TAG="SubCatPresenter"
        const val RESPONSE_OKAY=1
    }

}