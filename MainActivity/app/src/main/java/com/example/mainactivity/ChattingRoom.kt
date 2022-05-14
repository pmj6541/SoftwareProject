package com.example.mainactivity
import java.io.Serializable

data class ChattingRoom(
    val menu : String,
    val location : String,
    val name : String,
    val fullCount : Int
    ) : Serializable

