package com.learn.amazonapp.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.FragmentListOfOrderBinding
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.Order
import com.learn.amazonapp.presenter.ListOfOrderContract
import com.learn.amazonapp.presenter.ListOfOrderPresenter
import com.learn.amazonapp.presenter.checkout.SummaryPresenter
import com.learn.amazonapp.view.adapter.ListOfOrderAdapter
import com.learn.amazonapp.view.adapter.checkout.SummaryAdapter

class ListOfOrderFragment : Fragment() , ListOfOrderContract.IListOfOrderView{

    lateinit var binding : FragmentListOfOrderBinding
    lateinit var listOfItem: List<Order>
    lateinit var listOfOrderAdapter: ListOfOrderAdapter
    lateinit var listOfOrderPresenter: ListOfOrderPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListOfOrderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override var fragmentContext: Context
        get() = requireContext()
        set(value) {}

    override fun getListOfItemSuccess(listOfOrder: List<Order>) {
        listOfItem=listOfOrder
        setupRecyclerView()
    }

    override fun getListOfItemCategoryFail(error: String) {
        makeToast(error)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        intiPresenter()
        listOfOrderPresenter.getListOfOrder()
    }

    private fun setupRecyclerView(){
        binding.rvItem.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        listOfOrderAdapter= ListOfOrderAdapter(listOfItem)
        binding.rvItem.adapter=listOfOrderAdapter

        listOfOrderAdapter.setOnProductSelectedListener { product, i ->
            makeToast(product.toString())
        }
    }
    private fun intiPresenter() {
        listOfOrderPresenter= ListOfOrderPresenter(
            VolleyHandler(requireContext()),
            this)
    }

    fun makeToast(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }
    companion object {

    }


}