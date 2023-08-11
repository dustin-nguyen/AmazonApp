package com.learn.amazonapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.ActivityMainBinding
import com.learn.amazonapp.databinding.ActivitySplashScreenBinding
import com.learn.amazonapp.view.util.SharedPrefConstant

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        Handler().postDelayed({
            if(checkLogin()==true)
                goToActivity(MainActivity::class.java)
            else
                goToActivity(IntroActivity::class.java)
        }, 2000)

    }
    private fun goToActivity( cls: Class<*> ){
        val dataIntent = Intent(this, cls)
        startActivity(dataIntent)
        finish()
    }

    private fun checkLogin(): Boolean {
        val securedSharedPreferences = SharedPrefConstant.getSecuredSharedPref(this@SplashScreenActivity.applicationContext)
        val username = securedSharedPreferences.getString(SharedPrefConstant.USERNAME,"") ?:""
        //val password = securedSharedPreferences.getString(SharedPrefConstant.PASSWORD,"")
        if(username == "")
            return false
        return true
    }
}