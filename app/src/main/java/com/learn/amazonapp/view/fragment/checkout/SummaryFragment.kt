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
import com.learn.amazonapp.databinding.FragmentCartWebviewBinding
import com.learn.amazonapp.databinding.FragmentSummaryBinding
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.entity.Address
import com.learn.amazonapp.presenter.checkout.CheckoutPresenter
import com.learn.amazonapp.presenter.checkout.SummaryContract
import com.learn.amazonapp.presenter.checkout.SummaryPresenter
import com.learn.amazonapp.view.HomeCommunicator
import com.learn.amazonapp.view.adapter.checkout.SummaryAdapter

class SummaryFragment(
    val checkoutPresenter: CheckoutPresenter)
    : Fragment(),SummaryContract.ISummaryView {

    lateinit var binding: FragmentSummaryBinding
    lateinit var listOfItem: List<ProductInCart>
    lateinit var summaryPresenter: SummaryPresenter
    lateinit var summaryAdapter: SummaryAdapter

    lateinit var homeCommunicator: HomeCommunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeCommunicator=context as HomeCommunicator
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSummaryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }


    override fun onResume() {
        super.onResume()
        summaryPresenter.setupView()
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
    private fun setup() {
        intiPresenter()
        binding.btnPlaceOrder.setOnClickListener {
            homeCommunicator.goToFragment(ORDER_CONFIRM,OrderConfirmFragment(checkoutPresenter))
        }

    }
    private fun intiPresenter() {
        summaryPresenter= SummaryPresenter(checkoutPresenter,this)
    }
    private fun setupRecyclerView(){
        binding.rvItem.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        summaryAdapter= SummaryAdapter(listOfItem)
        binding.rvItem.adapter=summaryAdapter

        summaryAdapter.setOnProductSelectedListener { product, i ->
            makeToast(product.toString())
        }
    }
    fun makeToast(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }
    companion object {

        const val ORDER_CONFIRM="CONFIRMATION"

    }


}