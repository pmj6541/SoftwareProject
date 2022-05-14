package com.example.mainactivity

import java.io.Serializable

data class User(
    val Id : String,
    val location : String,
    val menu : String
    ) : Serializable
