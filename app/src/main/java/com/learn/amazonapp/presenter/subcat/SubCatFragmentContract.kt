package com.learn.amazonapp.presenter.subcat

import android.content.Context
import com.learn.amazonapp.model.remote.entity.Category
import com.learn.amazonapp.model.remote.entity.Subcategory

interface SubCatFragmentContract {
    interface ISubCatFragmentPresenter{
        fun getListOfSubCategory(id:String)
    }
    interface ISubCatFragmentView{
        var fragmentContext: Context
        fun getListOfSubCategorySuccess(listOfSubCategory: List<Subcategory>)
        fun getListOfSubCategoryFail(error: String)


    }
}