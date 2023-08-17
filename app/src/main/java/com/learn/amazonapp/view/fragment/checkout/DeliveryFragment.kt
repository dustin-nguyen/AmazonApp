package com.learn.amazonapp.view.fragment.checkout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.FragmentDeliveryBinding
import com.learn.amazonapp.databinding.FragmentShowItemBinding
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.Address
import com.learn.amazonapp.presenter.ListOfItem.ShowItemPresenter
import com.learn.amazonapp.presenter.checkout.DeliveryContract
import com.learn.amazonapp.presenter.checkout.DeliveryPresenter
import com.learn.amazonapp.view.adapter.ShowItemAdapter
import com.learn.amazonapp.view.adapter.checkout.DeliveryAdapter


class DeliveryFragment : Fragment(),DeliveryContract.IDeliveryView {
    lateinit var binding: FragmentDeliveryBinding
    lateinit var deliveryAdapter: DeliveryAdapter
    lateinit var listOfAddress: List<Address>
    lateinit var deliveryPresenter: DeliveryPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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



    companion object {

    }
}