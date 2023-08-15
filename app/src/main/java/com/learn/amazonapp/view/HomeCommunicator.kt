package com.learn.amazonapp.view

import androidx.fragment.app.Fragment

interface HomeCommunicator {
    fun goToFragment(backStackEntryName:String,fragment: Fragment)

}