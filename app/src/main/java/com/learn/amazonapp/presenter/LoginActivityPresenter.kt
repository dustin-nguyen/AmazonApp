package com.learn.amazonapp.presenter

class LoginActivityPresenter(
    private val loginActivityView: LoginActivityContract.ILoginActivityView)
    :LoginActivityContract.ILLoginActivityPresenter{
    override fun decideFragmentEvent(btnString: String, comparedString: String) {
        if(btnString== comparedString)
            loginActivityView.goToSignUpFragment()
        else
            loginActivityView.goToLoginFragment()
    }
}