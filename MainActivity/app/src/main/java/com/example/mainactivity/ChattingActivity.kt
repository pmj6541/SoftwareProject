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
import java.text.SimpleDateFormat
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
        val curUser = intent.getSerializableExtra("curUser") as User
        val roomID = intent.getStringExtra("roomID")
        mBinding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val database = FirebaseDatabase.getInstance()
        //val ref = database.getReference()


        var editText = binding.editTextTextPersonName3
        var button = binding.button3
        val addChat = ArrayList<String>()
        val tmpChat = ArrayList<String>()
        val tmpTimeStamp = ArrayList<String>()
        val tmpUserUID = ArrayList<String>()
        var chatSize = 0
        var start = true


        editText.setOnEditorActionListener { v, actionId, event ->
            val msgTime = System.currentTimeMillis()
            val sdf = SimpleDateFormat("yyyy-MM-dd-hh-mm")
            val timeStamp = sdf.format(msgTime)
            tmpChat.add(editText.text.toString())
            Log.v("MainActivity","tmpChat : "+tmpChat.size.toString())
            tmpTimeStamp.add(timeStamp)
            tmpUserUID.add(FirebaseAuth.getInstance().currentUser!!.uid)
            database.child("chattingrooms/$roomID/msg").setValue(tmpChat)
            database.child("chattingrooms/$roomID/msgTimeStamp").setValue(tmpTimeStamp)
            database.child("chattingrooms/$roomID/msgUserUID").setValue(tmpUserUID)

            chatSize += 1

            talk.clear()
            false //엔터 누른 후에 키보드 내려감
        }

        val multiRVAdapter = MultiRVAdapter(talk)
        database.child("chattingrooms").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot){
                talk.clear()
                tmpChat.clear()
                tmpTimeStamp.clear()
                tmpUserUID.clear()
                for(data in dataSnapshot.children){
                    if(data.key.toString() == roomID){
                        var modelResult = data.getValue(ChattingRoom::class.java)
                        chatSize = modelResult!!.msgUserUID.size
                        for(i in 0..modelResult!!.msgUserUID.size-1){
                            tmpChat.add(modelResult.msg[i])
                            tmpTimeStamp.add(modelResult.msgTimeStamp[i])
                            tmpUserUID.add(modelResult.msgUserUID[i])
                            talk.add(Chat(modelResult.msg[i],modelResult.msgTimeStamp[i],modelResult.msgUserUID[i]))

                        }
                        start = false
                        modelResult = null
                    }
                }
                multiRVAdapter.notifyDataSetChanged()


            }

        })
        Log.v("MainActivity","함수 밖이에요")


        binding.chatRv.adapter = multiRVAdapter
        binding.chatRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }


    override fun onDestroy() {
        super.onDestroy()
        talk.clear()
    }
}