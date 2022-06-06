package com.example.mainactivity
import java.io.Serializable

data class Chat(
    var msg : String = String(),
    var msgTimeStamp : String = String(),
    var msgUserUID : String = String(),
) : Serializable

