package com.learn.amazonapp.view.activity

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.navigation.NavigationView
import com.learn.amazonapp.Constant.PRODUCT_KEY
import com.learn.amazonapp.Constant.TO_CART_ACTION
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.ActivityMainBinding
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.Product
import com.learn.amazonapp.presenter.cart.CartPresenter
import com.learn.amazonapp.presenter.main.MainActivityContract
import com.learn.amazonapp.presenter.main.MainActivityPresenter
import com.learn.amazonapp.view.HomeCommunicator
import com.learn.amazonapp.view.fragment.CartFragment
import com.learn.amazonapp.view.fragment.HomeFragment
import com.learn.amazonapp.view.fragment.ListOfOrderFragment

class MainActivity : AppCompatActivity(),HomeCommunicator,MainActivityContract.IMainActivityView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityPresenter: MainActivityPresenter
    private lateinit var localBroadCastManager :LocalBroadcastManager
    private lateinit var cartPresenter: CartPresenter
    private var isReceiverRegistered = false

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            if(binding.main.isDrawerOpen(GravityCompat.START))
                binding.main.closeDrawer(GravityCompat.START)
            else
                binding.main.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override var context: Context
        get() = this
        set(value) {}

    override fun setTitle(title: String) {
        binding.tvTitle.text=title
    }

    override fun setNameAndEmail(username: String, email: String) {
        val navigationView =findViewById<NavigationView>(R.id.navViews)
        val headerView= navigationView.getHeaderView(0)
        val emailView=headerView.findViewById<TextView>(R.id.tv_email)
        val nameView=headerView.findViewById<TextView>(R.id.tv_name)

        emailView.text = email
        nameView.text = "Welcome $username"
    }

    override fun logout() {
        val dataIntent = Intent(this, SplashScreenActivity::class.java)
        startActivity(dataIntent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        // not work with theme switch
      //  unregisterReceiver(cartPresenter)
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
        registerLocalBroadCastRecevier()
        setup()
    }

    private fun setup() {
        initPresenter()
        setupDrawer()
        setupHeader()
        handleMenuEvent(HOME,HomeFragment())
        mainActivityPresenter.setNameAndEmail()
    }

    private fun initPresenter() {
        mainActivityPresenter= MainActivityPresenter(VolleyHandler(this),this)
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
                R.id.orders-> handleMenuEvent(ORDERS, ListOfOrderFragment())
                R.id.logout -> mainActivityPresenter.logout()
            }
            true
        }
    }

    private fun handleMenuEvent(backStackEntryName:String,fragment: Fragment){
        binding.main.closeDrawer(GravityCompat.START)
        supportFragmentManager.beginTransaction().replace(R.id.parent,fragment).addToBackStack(backStackEntryName).commit()
        mainActivityPresenter.decideTitleBasedOnFragment(fragment)
    }

    override fun goToFragment(backStackEntryName:String,fragment: Fragment) {
        handleMenuEvent(backStackEntryName,fragment)
    }

    override fun sendProductToCart(product: Product) {
        val localIntent = Intent(TO_CART_ACTION).putExtra(PRODUCT_KEY,product)
        localBroadCastManager.sendBroadcast(localIntent)
    }

    private fun registerLocalBroadCastRecevier() {
        localBroadCastManager = LocalBroadcastManager.getInstance(this)
        cartPresenter= CartPresenter()
        // register receiver
        localBroadCastManager.registerReceiver(
            cartPresenter, IntentFilter(TO_CART_ACTION)
        )
        isReceiverRegistered = true

    }

    private fun setupHeader(){
        val navigationView =findViewById<NavigationView>(R.id.navViews)
        val headerView= navigationView.getHeaderView(0)
        val switch=headerView.findViewById<SwitchCompat>(R.id.switchTheme)
         switch.setOnCheckedChangeListener { compoundButton, isChecked ->
             AppCompatDelegate.setDefaultNightMode(
                 if(isChecked)
                    AppCompatDelegate.MODE_NIGHT_YES
                 else
                    AppCompatDelegate.MODE_NIGHT_NO
             )
         }
    }

    companion object{
        const val HOME="HOME"
        const val CART_TITLE="Cart"
        const val ORDERS="Orders"
    }
}