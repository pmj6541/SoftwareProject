package com.example.mainactivity

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mainactivity.databinding.ActivityChattingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat

class ChattingActivity : AppCompatActivity() {
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

        var editText = binding.editTextTextPersonName3
        var button = binding.button3
        val tmpChat = ArrayList<String>()
        val tmpTimeStamp = ArrayList<String>()
        val tmpUserUID = ArrayList<String>()
        var chatSize = 0

        binding.chatname.text = chatroom.title
        editText.setOnEditorActionListener { _, _, _ ->
                val msgTime = System.currentTimeMillis()
                val sdf = SimpleDateFormat("yyyy-MM-dd-hh-mm")
                val timeStamp = sdf.format(msgTime)
                tmpChat.add(editText.text.toString())
                tmpTimeStamp.add(timeStamp)
                tmpUserUID.add(FirebaseAuth.getInstance().currentUser!!.uid)
                database.child("chattingrooms/$roomID/msg").setValue(tmpChat)
                database.child("chattingrooms/$roomID/msgTimeStamp").setValue(tmpTimeStamp)
                database.child("chattingrooms/$roomID/msgUserUID").setValue(tmpUserUID)
                chatSize += 1
                talk.clear()
                editText.text = null
                false //엔터 누른 후에 키보드 내려감
        }


        button.setOnClickListener{
            val msgTime = System.currentTimeMillis()
            val sdf = SimpleDateFormat("yyyy-MM-dd-hh-mm")
            val timeStamp = sdf.format(msgTime)
            tmpChat.add(editText.text.toString())
            tmpTimeStamp.add(timeStamp)
            tmpUserUID.add(FirebaseAuth.getInstance().currentUser!!.uid)
            database.child("chattingrooms/$roomID/msg").setValue(tmpChat)
            database.child("chattingrooms/$roomID/msgTimeStamp").setValue(tmpTimeStamp)
            database.child("chattingrooms/$roomID/msgUserUID").setValue(tmpUserUID)
            chatSize += 1
            talk.clear()
            editText.text=null
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken,0) //클릭한 후 키보드 내려감
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
                        if(FirebaseAuth.getInstance().currentUser!!.uid != modelResult!!.usersUID[0]){
                            binding.deletechat.visibility = View.GONE
                        }
                        chatSize = modelResult.msgUserUID.size
                        for(i in 0 until modelResult.msgUserUID.size){
                            tmpChat.add(modelResult.msg[i])
                            tmpTimeStamp.add(modelResult.msgTimeStamp[i])
                            tmpUserUID.add(modelResult.msgUserUID[i])
                            talk.add(Chat(modelResult.msg[i],modelResult.msgTimeStamp[i],modelResult.msgUserUID[i]))

                        }
                    }
                }
                binding.chatRv.scrollToPosition(talk.size-1) //메세지를 보낼 시 화면을 맨 밑으로 내림

            }

        })

        binding.backbtn.setOnClickListener{
            finish()
        }

        binding.deletechat.setOnClickListener {
            val builder : AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("채팅방 삭제")
            builder.setMessage("정말로 삭제하시겠습니까?")
            builder.setPositiveButton("예",DialogInterface.OnClickListener{_,_ ->
                val curChattingDB = FirebaseDatabase.getInstance().getReference("chattingrooms/$roomID")
                curChattingDB.removeValue()
                finish()
            })
            builder.setNegativeButton("아니오",null)
            builder.create().show()
        }


        binding.chatRv.adapter = multiRVAdapter
        binding.chatRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }


    override fun onDestroy() {
        super.onDestroy()
        talk.clear()
    }
}