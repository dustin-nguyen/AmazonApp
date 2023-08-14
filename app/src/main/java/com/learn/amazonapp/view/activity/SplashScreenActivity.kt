package com.learn.amazonapp.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.ActivitySplashScreenBinding
import com.learn.amazonapp.presenter.splash.SplashContract
import com.learn.amazonapp.presenter.splash.SplashPresenter

class SplashScreenActivity : AppCompatActivity() , SplashContract.ISplashView{
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var splashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    override var context: Context
        get() = this@SplashScreenActivity.applicationContext
        set(value) {}

    override fun navigateToHome() {
        goToActivity(MainActivity::class.java)
    }

    override fun navigateToIntro() {
        goToActivity(IntroActivity::class.java)
    }

    private fun setup() {
        startAnimation()
        initPresenter()
        Handler().postDelayed({
            splashPresenter.checkLogin()
        },2000)


    }
    private fun initPresenter(){
        splashPresenter= SplashPresenter(this)
    }
    private fun goToActivity( cls: Class<*> ){
        val dataIntent = Intent(this, cls)
        startActivity(dataIntent)
        finish()
    }
    private fun startAnimation(){
        binding.splashImg.startAnimation(
            AnimationUtils.loadAnimation(this@SplashScreenActivity,
                R.anim.bounce_anim))
    }


}