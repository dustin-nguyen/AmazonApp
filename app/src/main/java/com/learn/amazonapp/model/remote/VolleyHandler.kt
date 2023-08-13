package com.learn.amazonapp.model.remote

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.entity.CategoryResponse
import com.learn.amazonapp.model.remote.entity.LoginResponse
import org.json.JSONObject

class VolleyHandler(val context: Context)  {

    fun postLogin(emailId:String,password:String, responseCallBack: ResponseCallBack){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        jsonObject.put(KEY_EMAIL_ID, emailId)
        jsonObject.put(KEY_PASSWORD, password)


        val url = BASE_URL+ BASE_LOGIN

        val jsonRequest =object :JsonObjectRequest(Request.Method.POST, url, jsonObject,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response.toString())
                val typeToken = object : TypeToken<LoginResponse>(){}
                val loginResponse = Gson().fromJson(response.toString(),typeToken)
                responseCallBack.success(loginResponse)
            },
            Response.ErrorListener { error ->
                // Handle errors
                responseCallBack.failure(error.toString())
            }){
            override fun getHeaders(): MutableMap<String, String> {
                val header =HashMap<String,String>()
                header[CONTENT_TYPE]= APPLICATION_JSON
                return super.getHeaders()
            }
        }
        requestQueue.add(jsonRequest)

    }
    fun getCategory(responseCallBack: ResponseCallBack){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()

        val url = BASE_URL+ BASE_CATEGORY
        val jsonRequest =object :JsonObjectRequest(Request.Method.GET, url, jsonObject,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response.toString())
                val typeToken = object : TypeToken<CategoryResponse>(){}
                val categoryResponse = Gson().fromJson(response.toString(),typeToken)
                responseCallBack.success(categoryResponse)
            },
            Response.ErrorListener { error ->
                // Handle errors
                responseCallBack.failure(error.toString())
            }){
            override fun getHeaders(): MutableMap<String, String> {
                val header =HashMap<String,String>()
                header[CONTENT_TYPE]= APPLICATION_JSON
                return super.getHeaders()
            }
        }
        requestQueue.add(jsonRequest)
    }

    companion object{
        const val BASE_URL =" http://10.0.2.2/myshop/index.php/"
        const val BASE_LOGIN ="User/auth"
        const val BASE_CATEGORY ="Category"

        const val KEY_AUTHORIZATION="Authorization"
        const val CACHE_SIZE=40
        const val MAX_CACHE_SIZE_IN_BYTE= 1000000000
        const val KEY_KEYWORD="keywords"
        const val KEY_EMAIL_ID="email_id"
        const val KEY_PASSWORD="password"
        const val CONTENT_TYPE= "Content-type"
        const val APPLICATION_JSON="application/json"
    }
}