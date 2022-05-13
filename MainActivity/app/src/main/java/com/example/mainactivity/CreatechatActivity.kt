package com.example.mainactivity

import androidx.appcompat.app.AppCompatActivity
import com.example.mainactivity.databinding.ActivityCreatechatBinding
import com.google.firebase.auth.FirebaseAuth

class CreatechatActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivityCreatechatBinding? = null
    private val binding get() = mBinding!!
}