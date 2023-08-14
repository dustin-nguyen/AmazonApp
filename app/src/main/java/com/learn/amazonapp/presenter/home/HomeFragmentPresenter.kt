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
    init {
        imgMap["smartphones.png"]=R.drawable.smartphone
        imgMap["laptops.png"]=R.drawable.laptop
        imgMap["mensfashion.png"]=R.drawable.mensfashion
        imgMap["womensfashion.png"]=R.drawable.womensfashion
        imgMap["kidsfashion.png"]=R.drawable.kidsfashion
        imgMap["homeappliances.png"]=R.drawable.homeappliances
        imgMap["grocery.png"]=R.drawable.grocery
        imgMap["beauty_and_cosmetics.png"]=R.drawable.beauty_and_cosmetics
    }
    override fun getListOfCategory() {
        volleyHandler.getCategory(responseCallBack = object : ResponseCallBack {
            override fun success(getResponse: Any) {
                getResponse as CategoryResponse
                Log.i(TAG,getResponse.toString())
                var listOfCategory= replaceWithLocalImg(getResponse.categories)
                homeFragmentView.getListOfCategorySuccess(listOfCategory)

            }

            override fun failure(error: String) {
                Log.i(LoginFragmentPresenter.TAG,error)
                homeFragmentView.getListOfCategoryFail(error)
            }
        })
    }
    fun replaceWithLocalImg(listofCategory: List<Category>):List<Category>{
        for(category in listofCategory){
            category.category_image_url=(imgMap[category.category_image_url] ?: R.drawable.ic_launcher_background).toString()
        }
        return listofCategory
    }

    companion object{
        const val TAG="HomeFragmentPresenter"
        const val RESPONSE_OKAY=1
        val imgMap= HashMap<String,Int>()
    }

}