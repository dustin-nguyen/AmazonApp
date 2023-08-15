package com.learn.amazonapp.model.remote

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.entity.CategoryResponse
import com.learn.amazonapp.model.remote.entity.ListOfItemResponse
import com.learn.amazonapp.model.remote.entity.LoginResponse
import com.learn.amazonapp.model.remote.entity.ProductResponse
import com.learn.amazonapp.model.remote.entity.SubCatResponse
import com.learn.amazonapp.model.remote.entity.Subcategory
import org.json.JSONObject

class VolleyHandler(val context: Context)  {
//    lateinit var imgLoader: ImageLoader
//    init {
//        initializeImgLoader(context)
//    }
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
    fun getSubCategoryById(id:String,responseCallBack: ResponseCallBack){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()

        val url = "$BASE_URL$BASE_SUBCATEGORY?$KEY_CATEGORY_ID=$id"
        val jsonRequest =object :JsonObjectRequest(Request.Method.GET, url, jsonObject,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response.toString())
                val typeToken = object : TypeToken<SubCatResponse>(){}
                val subCategoryResponse = Gson().fromJson(response.toString(),typeToken)
                responseCallBack.success(subCategoryResponse)
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
    fun getItemBySubCatId(subCategoryId:String,responseCallBack: ResponseCallBack){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()

        val url = "$BASE_LIST_OF_ITEM/$subCategoryId"
        val jsonRequest =object :JsonObjectRequest(Request.Method.GET, url, jsonObject,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response.toString())
                val typeToken = object : TypeToken<ListOfItemResponse>(){}
                val listOfItemResponse = Gson().fromJson(response.toString(),typeToken)
                responseCallBack.success(listOfItemResponse)
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
    fun getItemById(id:String,responseCallBack: ResponseCallBack){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()

        val url = "$BASE_URL$BASE_PRODUCT/$id"
        val jsonRequest =object :JsonObjectRequest(Request.Method.GET, url, jsonObject,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response.toString())
                val typeToken = object : TypeToken<ProductResponse>(){}
                val product = Gson().fromJson(response.toString(),typeToken)
                responseCallBack.success(product.product)
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
        const val BASE_SUBCATEGORY ="SubCategory"
        const val KEY_CATEGORY_ID="category_id"
        const val BASE_LIST_OF_ITEM="$BASE_URL$BASE_SUBCATEGORY/products"
        const val BASE_PRODUCT="Product/details/"

        const val KEY_EMAIL_ID="email_id"
        const val KEY_PASSWORD="password"
        const val CONTENT_TYPE= "Content-type"
        const val APPLICATION_JSON="application/json"
    }
}