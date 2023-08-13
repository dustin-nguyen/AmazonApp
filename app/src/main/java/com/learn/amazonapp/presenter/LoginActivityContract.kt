package com.learn.amazonapp.presenter

interface LoginActivityContract {
    interface ILLoginActivityPresenter{
        fun decideFragmentEvent(btnString:String, compare:String)
    }
    interface ILoginActivityView{
        fun goToLoginFragment()
        fun goToSignUpFragment()

    }
}