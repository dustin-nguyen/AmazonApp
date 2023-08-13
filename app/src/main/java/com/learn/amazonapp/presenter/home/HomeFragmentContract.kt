package com.learn.amazonapp.presenter.home

import android.content.Context
import com.learn.amazonapp.model.remote.entity.Category

interface HomeFragmentContract {
    interface IHomeFragmentPresenter{
        fun getListOfCategory()
    }
    interface IHomeFragmentView{
        var fragmentContext: Context
        fun getListOfCategorySuccess(listOfCategory: List<Category>)
        fun getListOfCategoryFail(error: String)


    }
}