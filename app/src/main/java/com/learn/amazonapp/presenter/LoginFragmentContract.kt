package com.learn.amazonapp.presenter

import android.content.Context
import java.lang.Error

interface LoginFragmentContract {
    interface ILoginFragmentPresenter{

        fun login(emailId:String,password:String)
    }
    interface ILoginFragmentView{
        var fragmentContext: Context
        fun loginSuccess()
        fun loginFail(error: String)

    }
}