package com.learn.amazonapp.presenter.splash

import com.learn.amazonapp.view.util.SharedPrefConstant

class SplashPresenter(
                      private val splashView: SplashContract.ISplashView
)
    : SplashContract.ISplashPresenter {
    override fun checkLogin() {
        val securedSharedPreferences = SharedPrefConstant.getSecuredSharedPref(splashView.context)
        val username = securedSharedPreferences.getString(SharedPrefConstant.USERNAME,"") ?:""
        //val password = securedSharedPreferences.getString(SharedPrefConstant.PASSWORD,"")
        if(username == "")
            splashView.navigateToIntro()
        else
            splashView.navigateToHome()
    }


}