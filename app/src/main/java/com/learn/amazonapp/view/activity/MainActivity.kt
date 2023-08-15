package com.learn.amazonapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.ActivityMainBinding
import com.learn.amazonapp.presenter.home.HomeFragmentContract
import com.learn.amazonapp.presenter.main.MainActivityContract
import com.learn.amazonapp.presenter.main.MainActivityPresenter
import com.learn.amazonapp.presenter.splash.SplashPresenter
import com.learn.amazonapp.view.HomeCommunicator
import com.learn.amazonapp.view.fragment.CartFragment
import com.learn.amazonapp.view.fragment.HomeFragment

class MainActivity : AppCompatActivity(),HomeCommunicator,MainActivityContract.IMainActivityView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityPresenter: MainActivityPresenter

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            if(binding.main.isDrawerOpen(GravityCompat.START))
                binding.main.closeDrawer(GravityCompat.START)
            else
                binding.main.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setTitle(title: String) {
        binding.tvTitle.text=title
    }
    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStackImmediate()
            mainActivityPresenter.decideTitleBasedOnFragment(supportFragmentManager.findFragmentById(R.id.fragment_container))
        }else
            super.onBackPressed()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        initPresenter()
        setupDrawer()
        handleMenuEvent(HOME,HomeFragment())
    }

    private fun initPresenter() {
        mainActivityPresenter= MainActivityPresenter(this)
    }

    private fun setupDrawer(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_menu_24)
        }
        binding.navViews.setNavigationItemSelectedListener { menuItems->
            when(menuItems.itemId){
                R.id.home -> handleMenuEvent(HOME,HomeFragment())
                R.id.cart -> handleMenuEvent(CART_TITLE,CartFragment())
            }
            true
        }
    }
    private fun handleMenuEvent(backStackEntryName:String,fragment: Fragment){
        binding.main.closeDrawer(GravityCompat.START)
        supportFragmentManager.beginTransaction().replace(R.id.parent,fragment).addToBackStack(backStackEntryName).commit()
        mainActivityPresenter.decideTitleBasedOnFragment(fragment)
    }
    companion object{
        const val HOME="HOME"
        const val CART_TITLE="Cart"
    }

    override fun goToFragment(backStackEntryName:String,fragment: Fragment) {
        handleMenuEvent(backStackEntryName,fragment)
    }


}