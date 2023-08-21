package com.learn.amazonapp.presenter.splash

import android.content.Context

interface SplashContract {

    interface ISplashPresenter{

        fun checkLogin()
    }
    interface ISplashView{

        var context:Context

        fun navigateToHome()

        fun navigateToIntro()
    }
}