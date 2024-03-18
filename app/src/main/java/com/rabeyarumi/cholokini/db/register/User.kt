package com.rabeyarumi.cholokini.db.register

data class User(

    val name: String,
    val email: String,
    val password: String,
    val userType: String,
    var userId: String
)
