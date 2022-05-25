package com.example.mainactivity
import java.io.Serializable

data class ChattingRoom(
    var menu : String,
    var location : String,
    var title : String,
    var fullCount : Int
    ) : Serializable

