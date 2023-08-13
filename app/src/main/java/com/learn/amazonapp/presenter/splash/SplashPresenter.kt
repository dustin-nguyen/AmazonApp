package com.learn.amazonapp.presenter.splash

import com.learn.amazonapp.presenter.util.SharedPrefConstant
import com.learn.amazonapp.presenter.util.SharedPrefConstant.EMAIL_ID

class SplashPresenter(
                      private val splashView: SplashContract.ISplashView
)
    : SplashContract.ISplashPresenter {
    override fun checkLogin() {
        val securedSharedPreferences = SharedPrefConstant.getSecuredSharedPref(splashView.context)
        val email = securedSharedPreferences.getString(EMAIL_ID,"") ?:""
        //val password = securedSharedPreferences.getString(SharedPrefConstant.PASSWORD,"")
        if(email == "")
            splashView.navigateToIntro()
        else
            splashView.navigateToHome()
    }


}