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
import com.learn.amazonapp.Constant.ADDRESS
import com.learn.amazonapp.Constant.BILL_AMOUNT
import com.learn.amazonapp.Constant.DELIVERY_ADDRESS
import com.learn.amazonapp.Constant.ITEMS
import com.learn.amazonapp.Constant.PAYMENT_METHOD
import com.learn.amazonapp.Constant.PRODUCT_ID
import com.learn.amazonapp.Constant.QUANTITY
import com.learn.amazonapp.Constant.TITLE
import com.learn.amazonapp.Constant.UNIT_PRICE
import com.learn.amazonapp.Constant.USER_ID
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.ResponseCallBack
import com.learn.amazonapp.model.remote.entity.AddAddressResponse
import com.learn.amazonapp.model.remote.entity.Address
import com.learn.amazonapp.model.remote.entity.CategoryResponse
import com.learn.amazonapp.model.remote.entity.ListOfAddressResponse
import com.learn.amazonapp.model.remote.entity.ListOfItemResponse
import com.learn.amazonapp.model.remote.entity.LoginResponse
import com.learn.amazonapp.model.remote.entity.LogoutResponse
import com.learn.amazonapp.model.remote.entity.OrderResponse
import com.learn.amazonapp.model.remote.entity.PlaceOrderResponse
import com.learn.amazonapp.model.remote.entity.ProductResponse
import com.learn.amazonapp.model.remote.entity.SubCatResponse
import com.learn.amazonapp.model.remote.entity.Subcategory
import org.json.JSONArray
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
    fun getListOfAddressOfUser(userid:String,responseCallBack: ResponseCallBack){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()

        val url = "$BASE_URL$BASE_USER_LIST_ADDRESS/$userid"
        val jsonRequest =object :JsonObjectRequest(Request.Method.GET, url, jsonObject,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response.toString())
                val typeToken = object : TypeToken<ListOfAddressResponse>(){}
                val listOfAddress = Gson().fromJson(response.toString(),typeToken)
                responseCallBack.success(listOfAddress)
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
    fun addAddressForUser(userid:String, address: Address, responseCallBack: ResponseCallBack){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        jsonObject.put(USER_ID, userid)
        jsonObject.put(TITLE, address.title)
        jsonObject.put(ADDRESS, address.address)

        val url = "$BASE_URL$BASE_USER_ADDRESS"
        val jsonRequest =object :JsonObjectRequest(Method.POST, url, jsonObject,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response.toString())
                val typeToken = object : TypeToken<AddAddressResponse>(){}
                val addressResponse = Gson().fromJson(response.toString(),typeToken)
                responseCallBack.success(addressResponse)
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
    fun constructJSONObjectForPlaceOrder(productPlaceOrderObject: PlaceOrderObject):JSONObject{
        val jsonObject = JSONObject()
        jsonObject.put(USER_ID,productPlaceOrderObject.userid)

        val addressJSONObject=JSONObject()
        addressJSONObject.put(TITLE,productPlaceOrderObject.address.title)
        addressJSONObject.put(TITLE, productPlaceOrderObject.address.title)
        addressJSONObject.put(ADDRESS, productPlaceOrderObject.address.address)

        jsonObject.put(DELIVERY_ADDRESS,addressJSONObject)


        val itemJsonArray = JSONArray()

        for (product in productPlaceOrderObject.listOfProduct) {
            val itemObject = JSONObject()
            itemObject.put(PRODUCT_ID, product.product.product_id)
            itemObject.put(QUANTITY, product.quantity)
            itemObject.put(UNIT_PRICE,product.product.price)
            itemJsonArray.put(itemObject)
        }

        jsonObject.put(ITEMS,itemJsonArray)

        jsonObject.put(BILL_AMOUNT,productPlaceOrderObject.billAmount)
        jsonObject.put(PAYMENT_METHOD,productPlaceOrderObject.payMethod)


        return jsonObject

    }
    fun postPlaceOrder(productPlaceOrderObject:PlaceOrderObject, responseCallBack: ResponseCallBack){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = constructJSONObjectForPlaceOrder(productPlaceOrderObject)
        val url = "$BASE_URL$BASE_ORDER"

        val jsonRequest =object :JsonObjectRequest(Method.POST, url, jsonObject,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response.toString())
                val typeToken = object : TypeToken<PlaceOrderResponse>(){}
                val addressResponse = Gson().fromJson(response.toString(),typeToken)
                responseCallBack.success(addressResponse)
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

    fun getOrders(userid: String, responseCallBack: ResponseCallBack){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()

        val url = "$BASE_URL$BASE_GET_ORDERS$userid"
        val jsonRequest =object :JsonObjectRequest(Method.GET, url, jsonObject,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response.toString())
                val typeToken = object : TypeToken<OrderResponse>(){}
                val orderResponse = Gson().fromJson(response.toString(),typeToken)
                responseCallBack.success(orderResponse)
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

    fun postLogout(email_id:String, responseCallBack: ResponseCallBack){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        jsonObject.put(KEY_EMAIL_ID, email_id)


        val url = "$BASE_URL$BASE_LOGOUT"
        val jsonRequest =object :JsonObjectRequest(Method.POST, url, jsonObject,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response.toString())
                val typeToken = object : TypeToken<LogoutResponse>(){}
                val addressResponse = Gson().fromJson(response.toString(),typeToken)
                responseCallBack.success(addressResponse)
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
        const val BASE_USER_LIST_ADDRESS="User/addresses"
        const val BASE_USER_ADDRESS="User/address"
        const val BASE_LOGIN ="User/auth"
        const val BASE_LOGOUT ="User/logout"
        const val BASE_CATEGORY ="Category"
        const val BASE_ORDER="Order"
        const val BASE_GET_ORDERS="Order/userOrders/"
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