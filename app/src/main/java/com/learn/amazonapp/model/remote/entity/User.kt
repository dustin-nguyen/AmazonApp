package com.learn.amazonapp.model.remote.entity

data class User(
    val email_id: String,
    val full_name: String ?= "",
    val mobile_no: String,
    val user_id: String
)