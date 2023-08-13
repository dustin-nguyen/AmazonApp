package com.learn.amazonapp.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.learn.amazonapp.databinding.FragmentLoginBinding
import com.learn.amazonapp.model.remote.VolleyHandler
import com.learn.amazonapp.presenter.LoginFragmentContract
import com.learn.amazonapp.presenter.LoginFragmentPresenter
import com.learn.amazonapp.view.LoginCommunicator
import java.lang.Error


class LoginFragment : Fragment(),LoginFragmentContract.ILoginFragmentView {
    private lateinit var parentLoginCommunicator: LoginCommunicator
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginFragmentPresenter:LoginFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentLoginCommunicator=context as LoginCommunicator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    override var fragmentContext: Context
        get() = requireContext()
        set(value) {}

    override fun loginSuccess() {
        parentLoginCommunicator.loginSuccess()
    }

    override fun loginFail(error: String) {
        makeToast("Wrong Password or Email")
    }

    private fun setup() {
        setButtonTextInParent()
        intiPresenter()
        setupLoginButton()
    }

    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            loginFragmentPresenter.login(email,password)
        }
    }

    private fun intiPresenter() {
        loginFragmentPresenter= LoginFragmentPresenter(VolleyHandler(requireContext()),this)
    }

    private fun setButtonTextInParent(){
        parentLoginCommunicator.setMessage(GO_TO_SIGNUP_TEXT_BUTTON)
    }

    private fun makeToast(message: String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val GO_TO_SIGNUP_TEXT_BUTTON="Click here to sign up."
    }


}