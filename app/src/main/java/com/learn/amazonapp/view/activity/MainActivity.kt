package com.learn.amazonapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.ActivityMainBinding
import com.learn.amazonapp.view.HomeCommunicator
import com.learn.amazonapp.view.fragment.HomeFragment

class MainActivity : AppCompatActivity(),HomeCommunicator {
    private lateinit var binding: ActivityMainBinding

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            if(binding.main.isDrawerOpen(GravityCompat.START))
                binding.main.closeDrawer(GravityCompat.START)
            else
                binding.main.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack()
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
        setupDrawer()
        handleMenuEvent(HOME,HomeFragment())
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

            }
            true
        }
    }
    private fun handleMenuEvent(backStackEntryName:String,fragment: Fragment){
        binding.main.closeDrawer(GravityCompat.START)
        supportFragmentManager.beginTransaction().replace(R.id.parent,fragment).addToBackStack(backStackEntryName).commit()
    }
    companion object{
        const val HOME="HOME"
    }

    override fun goToFragment(backStackEntryName:String,fragment: Fragment) {
        handleMenuEvent(backStackEntryName,fragment)
    }
}