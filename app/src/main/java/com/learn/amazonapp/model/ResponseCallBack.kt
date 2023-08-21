package com.learn.amazonapp.model

interface ResponseCallBack {

    fun success(obj: Any)

    fun failure(error: String)

}