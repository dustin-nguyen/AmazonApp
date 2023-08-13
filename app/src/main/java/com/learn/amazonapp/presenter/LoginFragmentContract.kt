package com.learn.amazonapp.presenter

import java.lang.Error

interface LoginFragmentContract {
    interface ILoginFragmentPresenter{

        fun login(emailId:String,password:String)
    }
    interface ILoginFragmentView{
        fun loginSuccess()
        fun loginFail(error: String)

    }
}