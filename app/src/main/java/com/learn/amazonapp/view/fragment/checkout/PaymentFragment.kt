package com.learn.amazonapp.view.fragment.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.FragmentPaymentBinding
import com.learn.amazonapp.model.remote.entity.Address
import com.learn.amazonapp.presenter.checkout.CheckoutPresenter
import com.learn.amazonapp.presenter.checkout.DeliveryPresenter
import com.learn.amazonapp.view.adapter.checkout.DeliveryAdapter
import com.learn.amazonapp.view.adapter.checkout.PaymentAdapter

class PaymentFragment(
    val checkoutFragment: CheckoutFragment,
    val checkoutPresenter: CheckoutPresenter)
    : Fragment() {

    lateinit var binding: FragmentPaymentBinding
    lateinit var paymentAdapter: PaymentAdapter
    private var listOfPayMethod= mutableListOf<String>(
        "Cash On Delivery",
        "Internet Banking",
        "Debit / Credit Card",
        "PayPal")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentPaymentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setupRecyclerView()
        setupNextBtn()
    }

    private fun setupRecyclerView(){
        binding.rvPayment.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        paymentAdapter= PaymentAdapter(listOfPayMethod)
        binding.rvPayment.adapter=paymentAdapter
    }
    private fun setupNextBtn() {
        binding.btnNext.setOnClickListener {
            val selectedPayMethod = paymentAdapter.getSelectedPayMethod()
            if (selectedPayMethod != null) {
                checkoutPresenter.paymentMethod=selectedPayMethod
                checkoutFragment.onNextButtonClicked()
                makeToast("Selected PayMethod: ${selectedPayMethod}")
            } else {
                makeToast("Please select a payment method")
            }

        }
    }
    fun makeToast(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }
}