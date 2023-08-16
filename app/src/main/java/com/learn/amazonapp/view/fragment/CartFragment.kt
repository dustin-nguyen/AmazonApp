package com.learn.amazonapp.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.amazonapp.databinding.FragmentCartBinding
import com.learn.amazonapp.model.remote.entity.Product
import com.learn.amazonapp.presenter.cart.CartContract
import com.learn.amazonapp.presenter.cart.CartPresenter
import com.learn.amazonapp.view.HomeCommunicator
import com.learn.amazonapp.view.adapter.CartAdapter

class CartFragment : Fragment(), CartContract.ICartFragmentView {
    private lateinit var cartPresenter: CartPresenter
    lateinit var binding: FragmentCartBinding
    lateinit var listOfItem: List<Product>
    lateinit var cartAdapter: CartAdapter
    lateinit var parentHomeCommunicator: HomeCommunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentHomeCommunicator=context as HomeCommunicator
    }
    override var fragmentContext: Context
        get() = requireContext()
        set(value) {}

    override fun getListOfItemSuccess(listOfProduct: List<Product>) {
        listOfItem=listOfProduct
        setupNewBookRecyclerView()
    }

    override fun getListOfItemCategoryFail(error: String) {
        makeToast(error)
    }

    override fun updateTotalPrice(price: Int) {
        binding.tvTotalBill.text=price.toString()
    }

    override fun getTotalPrice(): Int {
        return  binding.tvTotalBill.text.toString().toInt()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        intiPresenter()
        cartPresenter.getAllItemInCart()
    }
    private fun intiPresenter() {
        cartPresenter= CartPresenter()
        cartPresenter.setFragmentView(this)
    }
    private fun setupNewBookRecyclerView(){
        binding.rvItem.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        cartAdapter= CartAdapter(listOfItem,cartPresenter)
        binding.rvItem.adapter=cartAdapter

        cartAdapter.setOnProductSelectedListener { product, i ->
            makeToast(product.toString())
        }


    }
    fun makeToast(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }
    companion object {

    }


}