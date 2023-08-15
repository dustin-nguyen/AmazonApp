package com.learn.amazonapp.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.FragmentLoginBinding
import com.learn.amazonapp.databinding.FragmentSubCatBinding
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.Category
import com.learn.amazonapp.model.remote.entity.Subcategory
import com.learn.amazonapp.presenter.LoginFragmentPresenter
import com.learn.amazonapp.presenter.home.HomeFragmentPresenter
import com.learn.amazonapp.presenter.subcat.SubCatFragmentContract
import com.learn.amazonapp.presenter.subcat.SubCatPresenter
import com.learn.amazonapp.view.LoginCommunicator
import com.learn.amazonapp.view.adapter.SubCatViewPageAdapter


class SubCatFragment(val category: Category) : Fragment(),SubCatFragmentContract.ISubCatFragmentView {
    private lateinit var binding:FragmentSubCatBinding
    private lateinit var viewPageAdapter:SubCatViewPageAdapter
    private lateinit var subCatPresenter: SubCatPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSubCatBinding.inflate(inflater,container,false)
        return binding.root
    }

    override var fragmentContext: Context
        get() = requireContext()
        set(value) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        intiPresenter()
        subCatPresenter.getListOfSubCategory(category.category_id)
    }

    override fun getListOfSubCategorySuccess(listOfSubCategory: List<Subcategory>) {
        setupWebViewTab(listOfSubCategory)
    }

    override fun getListOfSubCategoryFail(error: String) {
        createCustomActionSnackBarWithRetry(error)
    }
    private fun setupWebViewTab(listOfSubCategory: List<Subcategory>) {
        val fragments= mutableListOf<Fragment>()
        val tabTitles = mutableListOf<String>() // List to store tab titles

        for(subCat in listOfSubCategory){
            fragments.add(ShowItemFragment(subCat))
            tabTitles.add(subCat.subcategory_name)
        }
        viewPageAdapter = SubCatViewPageAdapter(fragments,requireActivity())

        with(binding){
            viewPage.adapter=viewPageAdapter
            TabLayoutMediator(tabLayout,viewPage){
                    tab,position ->
                tab.text= tabTitles[position]
            }.attach()
        }
    }
    private fun intiPresenter() {
        subCatPresenter= SubCatPresenter(VolleyHandler(requireContext()),this)
    }
    private fun createCustomActionSnackBarWithRetry(message:String) {
        val snackbar = Snackbar.make(binding.mainContainer,message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Retry?"){

        }
        snackbar.show()
    }
    companion object {

    }
}