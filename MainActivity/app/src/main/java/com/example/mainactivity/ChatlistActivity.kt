package com.example.mainactivity

import android.content.Intent
import android.os.Bundle
import android.renderscript.Sampler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mainactivity.databinding.ActivityChatlistBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.mainactivity.ChattingRoom as ChattingRoom

class ChatlistActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivityChatlistBinding? = null
    private val binding get() = mBinding!!
    private var chatrooms = ArrayList<ChattingRoom>()
    private val database = FirebaseDatabase.getInstance().getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChatlistBinding.inflate(layoutInflater)
        val intent = intent
        val curUser:User = intent.getSerializableExtra("curUser") as User
        setContentView(binding.root)

        val id_array = ArrayList<String>()
        val chatRVAdapter = ChatRVAdapter(chatrooms)

        database.child("chattingrooms").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot){
                for(data in dataSnapshot.children){
                    val modelResult = data.getValue(ChattingRoom::class.java)
                    if(curUser.menu == modelResult?.menu && curUser.location == modelResult?.location){
                        if (modelResult != null) {
                            chatrooms.add(modelResult)
                            id_array.add(data.key.toString())
                        }
                    }

                }
                chatRVAdapter.notifyDataSetChanged()
            }
        })

        binding.backbtn.setOnClickListener{
            finish()
        }


        binding.lstUser.adapter = chatRVAdapter
        binding.lstUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        chatRVAdapter.setMyItemClickListener(object: ChatRVAdapter.MyItemClickListener{
            override fun onItemClick(position : Int) {
                val entChattingRoom = chatrooms[position]
                val roomID = id_array[position]
                goNextActivity(entChattingRoom,roomID,curUser)
            }
        })

    }

    private fun goNextActivity(entChattingRoom: ChattingRoom,roomID: String, curUser: User){
        val intent : Intent = Intent(this,ChattingActivity::class.java)
        intent.putExtra("chattingroom",entChattingRoom)
        intent.putExtra("roomID",roomID)
        intent.putExtra("curUser",curUser)
        startActivity(intent)
    }
}