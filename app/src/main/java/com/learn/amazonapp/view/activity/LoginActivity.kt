package com.learn.amazonapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.ActivityLoginBinding
import com.learn.amazonapp.view.fragment.LoginFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>0)
            supportFragmentManager.popBackStack()
        else
            super.onBackPressed()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
       // handleMenuEvent(LOGIN, LoginFragment())
    }
    private fun handleMenuEvent(backStackEntryName:String,fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(backStackEntryName).commit()
    }
    companion object{
        const val TAG="LoginActivity"
        const val LOGIN="LOGIN"
        const val SIGNUP="SIGNUP"
    }
}