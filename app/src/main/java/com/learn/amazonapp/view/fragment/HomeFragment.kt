package com.learn.amazonapp.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.FragmentHomeBinding
import com.learn.amazonapp.databinding.FragmentLoginBinding
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.Category
import com.learn.amazonapp.presenter.LoginFragmentPresenter
import com.learn.amazonapp.presenter.home.HomeFragmentContract
import com.learn.amazonapp.presenter.home.HomeFragmentPresenter
import com.learn.amazonapp.view.adapter.HomeCategoryAdapter


class HomeFragment :Fragment(), HomeFragmentContract.IHomeFragmentView {
    private lateinit var listOfCategory: List<Category>
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeFragmentPresenter: HomeFragmentPresenter
    private lateinit var categoryAdapter: HomeCategoryAdapter

    override var fragmentContext: Context
        get() = requireContext()
        set(value) {}

    override fun getListOfCategorySuccess(category: List<Category>) {
        listOfCategory=category
        setupNewBookRecyclerView()
    }

    override fun getListOfCategoryFail(error: String) {
        createCustomActionSnackBarWithRetry()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        intiPresenter()
        homeFragmentPresenter.getListOfCategory()


    }
    private fun intiPresenter() {
        homeFragmentPresenter= HomeFragmentPresenter(VolleyHandler(requireContext()),this)
    }
    private fun createCustomActionSnackBarWithRetry() {
        val snackbar = Snackbar.make(binding.mainContainer,"Cannot get the list of category", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Retry?"){
            homeFragmentPresenter.getListOfCategory()
            categoryAdapter.notifyDataSetChanged()
        }
        snackbar.show()
    }
    private fun setupNewBookRecyclerView(){
        binding.rvHomeCategory.layoutManager=GridLayoutManager(requireContext(),NUM_OF_COLUMN)
//        binding.rvHomeCategory.layoutManager=
//            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        categoryAdapter= HomeCategoryAdapter(listOfCategory)
        binding.rvHomeCategory.adapter=categoryAdapter

        categoryAdapter.setOnCategorySelectedListener { category, i ->
            Toast.makeText(requireContext(),"selected ${category.category_name}", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        const val NUM_OF_COLUMN=2

    }


}