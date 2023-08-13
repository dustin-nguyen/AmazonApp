package com.learn.amazonapp.view.fragment.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.amazonapp.databinding.FragmentIntro3Binding
import com.learn.amazonapp.view.activity.LoginActivity

class Intro3Fragment : Fragment() {
    private lateinit var binding: FragmentIntro3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentIntro3Binding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        binding.btnJoin.setOnClickListener {
            val dataIntent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(dataIntent)
        }
    }

    companion object {

    }
}