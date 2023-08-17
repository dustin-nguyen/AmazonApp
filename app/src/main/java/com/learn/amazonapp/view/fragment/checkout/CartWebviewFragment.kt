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
import com.learn.amazonapp.databinding.FragmentCartBinding
import com.learn.amazonapp.databinding.FragmentCartWebviewBinding
import com.learn.amazonapp.databinding.ViewHolderCartCheckoutBinding
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.presenter.cart.CartPresenter
import com.learn.amazonapp.presenter.checkout.CartCheckoutContract
import com.learn.amazonapp.presenter.checkout.CartCheckoutPresenter
import com.learn.amazonapp.view.adapter.CartAdapter
import com.learn.amazonapp.view.adapter.checkout.CartWebviewAdapter

class CartWebviewFragment : Fragment(),CartCheckoutContract.ICartCheckoutFragmentView {
    lateinit var binding:FragmentCartWebviewBinding
    lateinit var listOfItem: List<ProductInCart>
    lateinit var cartPresenter: CartCheckoutPresenter
    lateinit var cartAdapter: CartWebviewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCartWebviewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }



    override var fragmentContext: Context
        get() = requireContext()
        set(value) {}

    override fun getListOfItemSuccess(listOfProduct: ArrayList<ProductInCart>) {
        listOfItem=listOfProduct
        setupRecyclerView()
    }

    override fun getListOfItemCategoryFail(error: String) {
        makeToast(error)
    }

    override fun getTotalPriceSuccess(totalPrice: Int) {
        binding.tvTotalBill.text=totalPrice.toString()
    }
    private fun setup() {
        intiPresenter()
        cartPresenter.setTotalPrice()
        cartPresenter.getAllItemInCart()
    }
    private fun intiPresenter() {
        cartPresenter= CartCheckoutPresenter(this)

    }
    private fun setupRecyclerView(){
        binding.rvItem.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        cartAdapter= CartWebviewAdapter(listOfItem)
        binding.rvItem.adapter=cartAdapter

        cartAdapter.setOnProductSelectedListener { product, i ->
            makeToast(product.toString())
        }



    }
    fun makeToast(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }
}