package com.learn.amazonapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.amazonapp.R
import com.learn.amazonapp.view.LoginCommunicator


class SignupFragment : Fragment() {
    private lateinit var parentLoginCommunicator: LoginCommunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentLoginCommunicator=context as LoginCommunicator

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        parentLoginCommunicator.setMessage(GO_TO_LOGIN_TEXT_BUTTON)
    }

    companion object {
        const val GO_TO_LOGIN_TEXT_BUTTON="Click here to login."
    }
}