package com.example.mainactivity

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MultiRVAdapter(private var chat: ArrayList<Chat>): RecyclerView.Adapter<MultiRVAdapter.ChatViewHolder>() {
    private var firebaseAuth : FirebaseAuth? = null

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
        firebaseAuth = Firebase.auth
        if(firebaseAuth?.currentUser?.uid.toString() == chat[position].msgUserUID) {
//            holder.textView_message.setBackgroundResource(R.drawable.rightbubble)
            holder.textView_name.visibility = View.INVISIBLE
            holder.layout_main.gravity = Gravity.RIGHT
            holder.layout_sub.gravity = Gravity.RIGHT
        } else {
            holder.textView_message.setBackgroundColor(R.color.purple_200)
//            holder.textView_message.setBackgroundResource(R.drawable.leftbubble)
            holder.textView_name.text = chat[position].msgUserUID
            holder.textView_name.visibility = View.INVISIBLE
            holder.layout_main.gravity = Gravity.RIGHT
            holder.layout_sub.gravity = Gravity.RIGHT
        }
    }

    override fun getItemCount(): Int = chat.size


    inner class ChatViewHolder(view : View): RecyclerView.ViewHolder(view){
        val textView_name: TextView= view.findViewById(R.id.chat_textview_name)
        val textView_message: TextView = view.findViewById(R.id.chat_textview_title)
        val textView_time: TextView = view.findViewById(R.id.chat_item_textview_lastmessage)
        val layout_main: LinearLayout = view.findViewById(R.id.chatitem_linearlayout_main)
        val layout_sub: LinearLayout = view.findViewById(R.id.chatitem_linearlayout_sub)

    }



}