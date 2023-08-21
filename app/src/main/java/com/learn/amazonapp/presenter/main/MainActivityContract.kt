package com.learn.amazonapp.presenter.main

import android.content.Context
import androidx.fragment.app.Fragment

interface MainActivityContract {

    interface IMainActivityPresenter{

        fun decideTitleBasedOnFragment(fragment: Fragment?)

        fun setNameAndEmail()

        fun logout()
    }
    interface IMainActivityView{

        var context:Context

        fun setTitle(title:String)

        fun setNameAndEmail(username:String, email:String)

        fun logout()
    }
}