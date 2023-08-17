package com.learn.amazonapp.view.fragment.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.FragmentCheckoutBinding
import com.learn.amazonapp.databinding.FragmentSubCatBinding
import com.learn.amazonapp.model.remote.entity.Subcategory
import com.learn.amazonapp.view.adapter.SubCatViewPageAdapter
import com.learn.amazonapp.view.adapter.checkout.CheckoutViewPageAdapter
import com.learn.amazonapp.view.fragment.ShowItemFragment

class CheckoutFragment : Fragment() {
    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var viewPageAdapter: CheckoutViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCheckoutBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setupWebViewTab()
    }
    private fun setupWebViewTab() {
        val fragments= mutableListOf<Fragment>(CartWebviewFragment(),DeliveryFragment(),PaymentFragment(),SummaryFragment())
        val tabTitles = mutableListOf<String>("Cart items","Delivery","Payment","Summary") // List to store tab titles


        viewPageAdapter = CheckoutViewPageAdapter(fragments,requireActivity())

        with(binding){
            viewPage.adapter=viewPageAdapter
            TabLayoutMediator(tabLayout,viewPage){
                    tab,position ->
                tab.text= tabTitles[position]
            }.attach()
        }
    }

    companion object {

    }
}