package com.example.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mainactivity.databinding.ActivityChattingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChattingActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivityChattingBinding? = null
    private val binding get() = mBinding!!
    private var chatroom = ChattingRoom()
    private val database = FirebaseDatabase.getInstance().getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChattingBinding.inflate(layoutInflater)
        val intent = intent
        val curUser:User = intent.getSerializableExtra("curUser") as User
        setContentView(binding.root)

        val multiRVAdapter = MultiRVAdapter(chatroom)

        database.child("chattingroom").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot){
                multiRVAdapter.notifyDataSetChanged()
            }
        })
    }
}