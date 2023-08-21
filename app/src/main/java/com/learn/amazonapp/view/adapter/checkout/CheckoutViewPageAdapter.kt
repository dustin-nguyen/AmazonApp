package com.learn.amazonapp.view.adapter.checkout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CheckoutViewPageAdapter (
    private val fragment: List<Fragment>,
    fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return fragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }
}