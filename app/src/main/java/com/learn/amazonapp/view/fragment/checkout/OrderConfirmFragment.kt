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
import com.learn.amazonapp.databinding.FragmentOrderConfirmBinding
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.Address
import com.learn.amazonapp.presenter.checkout.CheckoutPresenter
import com.learn.amazonapp.presenter.checkout.OrderConfirmContract
import com.learn.amazonapp.presenter.checkout.OrderConfirmPresenter
import com.learn.amazonapp.presenter.checkout.SummaryPresenter
import com.learn.amazonapp.view.adapter.checkout.OrderConfirmAdapter
import com.learn.amazonapp.view.adapter.checkout.SummaryAdapter

class OrderConfirmFragment(
    val checkoutPresenter: CheckoutPresenter)
    : Fragment(),OrderConfirmContract.IOrderConfirmView {

    lateinit var binding: FragmentOrderConfirmBinding
    lateinit var listOfItem: List<ProductInCart>
    lateinit var orderConfirmPresenter: OrderConfirmPresenter
    lateinit var orderConfirmAdapter: OrderConfirmAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentOrderConfirmBinding.inflate(inflater,container,false)
        return binding.root
    }
    override var fragmentContext: Context
        get() = requireContext()
        set(value) {}

    override fun setTotalPrice(totalPrice: Int) {
        binding.tvTotalBill.text=totalPrice.toString()
    }

    override fun getListOfItemSuccess(listOfProduct: List<ProductInCart>) {
        listOfItem=listOfProduct
        setupRecyclerView()
    }

    override fun setPayMethod(payment: String) {
        binding.tvPayment.text= payment
    }

    override fun setDelivery(delivery: Address) {
        binding.tvDelivery.text= "${delivery.title}\n ${delivery.address}"
    }

    override fun setOrderIDAndStatus(orderId: String, status: String) {
        binding.tvOrderNumber.text= orderId
        binding.tvOrderStatus.text= status
    }

    override fun placeOrderFail(error: String) {
        makeToast(error)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        intiPresenter()
        orderConfirmPresenter.placeOrder()
    }
    private fun intiPresenter() {
        orderConfirmPresenter= OrderConfirmPresenter(
            VolleyHandler(requireContext()),
            checkoutPresenter,this)

    }
    private fun setupRecyclerView(){
        binding.rvItem.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        orderConfirmAdapter= OrderConfirmAdapter(listOfItem)
        binding.rvItem.adapter=orderConfirmAdapter

        orderConfirmAdapter.setOnProductSelectedListener { product, i ->
            makeToast(product.toString())
        }
    }
    fun makeToast(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }
}