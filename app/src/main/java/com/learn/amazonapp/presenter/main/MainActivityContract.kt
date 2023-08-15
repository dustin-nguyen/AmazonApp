package com.learn.amazonapp.presenter.main

import androidx.fragment.app.Fragment

interface MainActivityContract {
    interface IMainActivityPresenter{
        fun decideTitleBasedOnFragment(fragment: Fragment?)
    }
    interface IMainActivityView{
        fun setTitle(title:String)


    }
}