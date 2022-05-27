package com.example.mainactivity

import java.io.Serializable

data class DBUser(
    val email : String,
    val name: String,
    val password : String
    ) : Serializable
