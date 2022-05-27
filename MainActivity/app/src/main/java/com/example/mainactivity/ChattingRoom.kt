package com.example.mainactivity
import java.io.Serializable
import java.sql.Timestamp

data class ChattingRoom(
    var menu : String = "",
    var location : String = "",
    var title : String = "",
    var fullCount : Int = 0,
    var usersUID : ArrayList<String> = ArrayList<String>(),
    var msg : ArrayList<String> = ArrayList<String>(),
    var msgTimeStamp : ArrayList<String> = ArrayList<String>(),
    var msgUserUID : ArrayList<String> = ArrayList<String>(),
    ) : Serializable

