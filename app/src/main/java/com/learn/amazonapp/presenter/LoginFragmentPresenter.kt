package com.learn.amazonapp.presenter

import android.util.Log
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.LoginResponse

class LoginFragmentPresenter(private val volleyHandler: VolleyHandler,
                             private val loginFragmentView: LoginFragmentContract.ILoginFragmentView)
    :LoginFragmentContract.ILoginFragmentPresenter {
    override fun login(emailId: String, password: String) {
        volleyHandler.postLogin(emailId,password,responseCallBack = object : ResponseCallBack{
            override fun success(loginResposne: Any) {
                loginResposne as LoginResponse
                Log.i(TAG,loginResposne.toString())
                loginFragmentView.loginSuccess()
            }

            override fun failure(error: String) {
                Log.i(TAG,error)
                loginFragmentView.loginFail(error)
            }
        })
    }
    companion object{
        const val TAG="LoginFragmentPresenter"
    }
}