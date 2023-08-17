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
import com.learn.amazonapp.databinding.FragmentHomeBinding
import com.learn.amazonapp.databinding.FragmentShowItemBinding
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.Product
import com.learn.amazonapp.model.remote.entity.Subcategory
import com.learn.amazonapp.presenter.ListOfItem.ShowItemFragmentContract
import com.learn.amazonapp.presenter.ListOfItem.ShowItemPresenter
import com.learn.amazonapp.presenter.subcat.SubCatPresenter
import com.learn.amazonapp.view.HomeCommunicator
import com.learn.amazonapp.view.LoginCommunicator
import com.learn.amazonapp.view.adapter.ShowItemAdapter


class ShowItemFragment(val subCat:Subcategory) : Fragment(),ShowItemFragmentContract.IShowItemFragmentView {
    lateinit var binding:FragmentShowItemBinding
    lateinit var showItemAdapter: ShowItemAdapter
    lateinit var listOfItem: List<Product>
    lateinit var showItemPresenter:ShowItemPresenter
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
        setupRecyclerView()
    }

    override fun getListOfItemCategoryFail(error: String) {
        makeToast(error)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentShowItemBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        intiPresenter()
        showItemPresenter.getListOfItem(subCat.category_id)
    }
    private fun intiPresenter() {
        showItemPresenter= ShowItemPresenter(VolleyHandler(requireContext()),this)
    }

    private fun setupRecyclerView(){
        binding.rvItem.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        showItemAdapter= ShowItemAdapter(listOfItem,parentHomeCommunicator)
        binding.rvItem.adapter=showItemAdapter

        showItemAdapter.setOnProductSelectedListener { product, i ->
            makeToast(product.toString())
        }

    }
     fun makeToast(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }
    companion object {

    }


}