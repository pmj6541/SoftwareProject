package com.example.mainactivity

import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MultiRVAdapter(private var chat: ArrayList<Chat>): RecyclerView.Adapter<MultiRVAdapter.ChatViewHolder>() {
    private var firebaseAuth : FirebaseAuth? = null
    private val database = FirebaseDatabase.getInstance().getReference()

    interface MyItemClickListener{
        fun onItemClick()
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MultiRVAdapter.ChatViewHolder {
        firebaseAuth = Firebase.auth
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_chat, viewGroup, false)
        return ChatViewHolder(view)
    }


    override fun onBindViewHolder(holder: MultiRVAdapter.ChatViewHolder, position: Int) {
        holder.textView_message.text = chat[position].msg
        holder.textView_time.text = chat[position].msgTimeStamp
        val userUID = chat[position].msgUserUID
        var userName = ""
        database.child("users/$userUID/name").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val modelResult = snapshot.getValue(String::class.java)
                if(modelResult == null){
                    userName = "(unknown)"
                }else{
                    userName = modelResult.toString()
                }

                Log.v("MainActivity",userName)
                holder.textView_name.text = userName
            }
        })
        firebaseAuth = Firebase.auth
        if(firebaseAuth?.currentUser?.uid.toString() == chat[position].msgUserUID) {
            holder.textView_message.setBackgroundResource(R.drawable.ic_chat_right_item)
            holder.textView_name.visibility = View.INVISIBLE
            holder.layout_main.gravity = Gravity.RIGHT
            holder.layout_sub.gravity = Gravity.RIGHT
            holder.textView_message.setTextColor(Color.parseColor("#ffffff"))
        } else {
            holder.textView_message.setBackgroundResource(R.drawable.ic_chat_left_item)
//            holder.textView_message.setBackgroundResource(R.drawable.leftbubble)
            holder.layout_main.gravity = Gravity.LEFT
            holder.layout_sub.gravity = Gravity.LEFT
            holder.textView_message.setTextColor(Color.parseColor("#000000"))

        }
    }

    override fun getItemCount(): Int = chat.size


    inner class ChatViewHolder(view : View): RecyclerView.ViewHolder(view){
        val textView_name: TextView= view.findViewById(R.id.chat_textview_username)
        val textView_message: TextView = view.findViewById(R.id.chat_textview_chatcontent)
        val textView_time: TextView = view.findViewById(R.id.chat_item_textview_chattime)
        val layout_main: LinearLayout = view.findViewById(R.id.chatitem_linearlayout_main)
        val layout_sub: LinearLayout = view.findViewById(R.id.chatitem_linearlayout_sub)

    }



}