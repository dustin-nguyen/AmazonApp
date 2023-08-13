package com.learn.amazonapp.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.amazonapp.R
import com.learn.amazonapp.databinding.FragmentHomeBinding
import com.learn.amazonapp.databinding.FragmentLoginBinding
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.model.remote.entity.Category
import com.learn.amazonapp.presenter.LoginFragmentPresenter
import com.learn.amazonapp.presenter.home.HomeFragmentContract
import com.learn.amazonapp.presenter.home.HomeFragmentPresenter


class HomeFragment :Fragment(), HomeFragmentContract.IHomeFragmentView {
    private lateinit var listOfCategory: List<Category>
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeFragmentPresenter: HomeFragmentPresenter

    override var fragmentContext: Context
        get() = requireContext()
        set(value) {}

    override fun getListOfCategorySuccess(category: List<Category>) {
        listOfCategory=category
    }

    override fun getListOfCategoryFail(error: String) {

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


    companion object {

    }


}