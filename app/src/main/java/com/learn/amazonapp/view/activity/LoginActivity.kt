package com.learn.amazonapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.ActivityLoginBinding
import com.learn.amazonapp.presenter.LoginActivityContract
import com.learn.amazonapp.presenter.LoginActivityPresenter
import com.learn.amazonapp.view.LoginCommunicator
import com.learn.amazonapp.view.fragment.LoginFragment
import com.learn.amazonapp.view.fragment.SignupFragment

class LoginActivity : AppCompatActivity(),LoginCommunicator,LoginActivityContract.ILoginActivityView {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginActivityPresenter: LoginActivityPresenter

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStackImmediate()
        }
        else
            super.onBackPressed()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }
    override fun setMessage(message: String) {
        binding.btnChangeFragment.text=message
    }

    override fun loginSuccess() {
        goToActivity(MainActivity::class.java)
    }

    override fun goToLoginFragment() {
        handleMenuEvent(LOGIN, LoginFragment())
    }

    override fun goToSignUpFragment() {
        handleMenuEvent(SIGNUP, SignupFragment())
    }


    private fun setup() {
        displayLoginFragment()
        setupSwitchFragmentButton()
        initPresenter()

    }

    private fun initPresenter() {
       loginActivityPresenter= LoginActivityPresenter(this)
    }

    private fun displayLoginFragment(){
        handleMenuEvent(LOGIN, LoginFragment())
    }
    private fun setupSwitchFragmentButton(){
        binding.btnChangeFragment.setOnClickListener {
            with(binding){
                loginActivityPresenter.decideFragmentEvent(btnChangeFragment.text.toString(), GO_TO_SIGNUP_TEXT_BUTTON)

            }

        }
    }

    private fun handleMenuEvent(backStackEntryName:String,fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(backStackEntryName).commit()
    }
    private fun goToActivity( cls: Class<*> ){
        val dataIntent = Intent(this, cls)
        startActivity(dataIntent)
        finish()
    }
    companion object{
        const val TAG="LoginActivity"
        const val LOGIN="LOGIN"
        const val GO_TO_SIGNUP_TEXT_BUTTON="Click here to sign up."
        const val GO_TO_LOGIN_TEXT_BUTTON="Click here to login."
        const val SIGNUP="SIGNUP"
    }



}