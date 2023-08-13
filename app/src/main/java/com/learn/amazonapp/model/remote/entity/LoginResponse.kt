package com.learn.amazonapp.model.remote.entity

data class LoginResponse(
    val status: Int,
    val message: String,
    val user: User ?= User("","","","")
)