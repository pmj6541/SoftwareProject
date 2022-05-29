package com.example.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mainactivity.databinding.ActivityChattingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.stream.IntStream.range

class ChattingActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivityChattingBinding? = null
    private val binding get() = mBinding!!
    private val database = FirebaseDatabase.getInstance().getReference()
    private var talk = ArrayList<Chat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val chatroom = intent.getSerializableExtra("chattingroom") as ChattingRoom
        val roomID = intent.getStringExtra("roomID")
        mBinding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val multiRVAdapter = MultiRVAdapter(talk)
        database.child("chattingrooms").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot){
                for(data in dataSnapshot.children){
                    if(data.key.toString() == roomID){
                        val modelResult = data.getValue(ChattingRoom::class.java)

                        for(i in 0..modelResult?.msg!!.size-1){
                            talk.add(Chat(modelResult.msg[i],modelResult.msgTimeStamp[i],modelResult.msgUserUID[i]))
                        }
                    }
                }
                multiRVAdapter.notifyDataSetChanged()
            }

        })

        binding.chatRv.adapter = multiRVAdapter
        binding.chatRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}