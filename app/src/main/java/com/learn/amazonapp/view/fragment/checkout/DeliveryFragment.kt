package com.learn.amazonapp.view.fragment.checkout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.amazonapp.databinding.FragmentDeliveryBinding
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.Address
import com.learn.amazonapp.presenter.checkout.CheckoutPresenter
import com.learn.amazonapp.presenter.checkout.DeliveryContract
import com.learn.amazonapp.presenter.checkout.DeliveryPresenter
import com.learn.amazonapp.view.adapter.checkout.DeliveryAdapter


class DeliveryFragment(
    val checkoutFragment: CheckoutCommunicator,
    val checkoutPresenter: CheckoutPresenter)
    : Fragment(),DeliveryContract.IDeliveryView {

    lateinit var binding: FragmentDeliveryBinding
    lateinit var deliveryAdapter: DeliveryAdapter
    lateinit var listOfAddress: List<Address>
    lateinit var deliveryPresenter: DeliveryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDeliveryBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }
    override var fragmentContext: Context
        get() = requireContext()
        set(value) {}

    override fun getListOfItemSuccess(listOfAddress: List<Address>) {
        this.listOfAddress=listOfAddress
        setupRecyclerView()
    }

    override fun getListOfItemCategoryFail(error: String) {
        makeToast(error)
    }


    private fun setup() {
        intiPresenter()
        deliveryPresenter.getListOfAddressWithCurrentUser()
        setupBtn()
    }

    private fun setupBtn() {
        binding.btnNext.setOnClickListener {
            val selectedAddress = deliveryAdapter.getSelectedAddress()
            if (selectedAddress != null) {
                checkoutPresenter.address=selectedAddress
                checkoutFragment.onNextButtonClicked()
                makeToast("Selected Address: ${selectedAddress.title}, ${selectedAddress.address}")
            } else {
                makeToast("Please select an address")
            }

        }
    }

    private fun setupRecyclerView(){
        binding.rvAddress.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        deliveryAdapter= DeliveryAdapter(listOfAddress)
        binding.rvAddress.adapter=deliveryAdapter


    }
    private fun intiPresenter() {
        deliveryPresenter= DeliveryPresenter(VolleyHandler(requireContext()),this)
    }
    fun makeToast(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }
}