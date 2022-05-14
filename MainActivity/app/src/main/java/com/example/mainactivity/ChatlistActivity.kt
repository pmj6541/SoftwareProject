package com.example.mainactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainactivity.databinding.ActivityChatlistBinding
import com.example.mainactivity.databinding.ActivityCreatechatBinding
import com.example.mainactivity.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChatlistActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivityChatlistBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChatlistBinding.inflate(layoutInflater)
        val intent = intent
        var curUser:User = intent.getSerializableExtra("curUser") as User
        Toast.makeText(this,"현재 사용자 : ${curUser.Id}\n현재 사용자 스팟 : ${curUser.location}\n사용자가 선택한 음식 : ${curUser.menu}",Toast.LENGTH_SHORT).show()
        setContentView(binding.root)
    }
}