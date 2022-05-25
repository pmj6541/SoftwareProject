package com.example.mainactivity
import java.io.Serializable

data class ChattingRoom(
    var menu : String = "",
    var location : String = "",
    var title : String = "",
    var fullCount : Int = 0,
    var usersUID : ArrayList<String> = ArrayList<String>(),
    var msg : ArrayList<String> = ArrayList<String>(),
    ) : Serializable

