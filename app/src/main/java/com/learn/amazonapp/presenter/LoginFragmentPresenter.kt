package com.learn.amazonapp.presenter

import android.util.Log
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.LoginResponse
import com.learn.amazonapp.model.remote.entity.User
import com.learn.amazonapp.presenter.util.SharedPrefConstant
import com.learn.amazonapp.presenter.util.SharedPrefConstant.EMAIL_ID
import com.learn.amazonapp.presenter.util.SharedPrefConstant.FULL_NAME
import com.learn.amazonapp.presenter.util.SharedPrefConstant.MOBILE_NO
import com.learn.amazonapp.presenter.util.SharedPrefConstant.USER_ID

class LoginFragmentPresenter(
    private val volleyHandler: VolleyHandler,
    private val loginFragmentView: LoginFragmentContract.ILoginFragmentView)
    :LoginFragmentContract.ILoginFragmentPresenter {

    override fun login(emailId: String, password: String) {
        volleyHandler.postLogin(emailId,password,responseCallBack = object : ResponseCallBack{
            override fun success(loginResponse: Any) {
                loginResponse as LoginResponse
                Log.i(TAG,loginResponse.toString())
                if(loginResponse.status == RESPONSE_OKAY){
                    loginFragmentView.loginSuccess()
                    setLogin(loginResponse.user!!)
                }
                else
                    loginFragmentView.loginFail(loginResponse.message)
            }
            override fun failure(error: String) {
                Log.i(TAG,error)
                loginFragmentView.loginFail(error)
            }
        })
    }

    fun setLogin(user : User){
        val securedSharedPreferences = SharedPrefConstant.getSecuredSharedPref(loginFragmentView.fragmentContext)
        val securedEditor = securedSharedPreferences.edit()

        securedEditor.putString(EMAIL_ID,user.email_id)
        securedEditor.putString(FULL_NAME,user.full_name)
        securedEditor.putString(USER_ID,user.user_id)
        securedEditor.putString(MOBILE_NO,user.mobile_no)

        securedEditor.apply()
    }
    companion object{
        const val TAG="LoginFragmentPresenter"
        const val RESPONSE_OKAY=0
    }
}