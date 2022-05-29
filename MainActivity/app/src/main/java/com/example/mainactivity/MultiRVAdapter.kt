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
//        val view: View?
        firebaseAuth = Firebase.auth
//        val binding: ItemRightchatBinding = ItemRightchatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_chat, viewGroup, false)

        return ChatViewHolder(view)
//        if(firebaseAuth?.currentUser?.uid.toString() == chat[0].msgUserUID) {
//            val binding: ItemRightchatBinding = ItemRightchatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
//            return ViewHolder(binding)
//        } else {
//            val binding: ItemRightchatBinding = ItemRightchatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
//            return ViewHolder(binding)
//        }
    }


    override fun onBindViewHolder(holder: MultiRVAdapter.ChatViewHolder, position: Int) {
        holder.textView_message.text = chat[position].msg
        holder.textView_time.text = chat[position].msgTimeStamp
        firebaseAuth = Firebase.auth
        if(firebaseAuth?.currentUser?.uid.toString() == chat[position].msgUserUID) {
//            holder.textView_message.setBackgroundResource(R.drawable.rightbubble)
            holder.textView_name.visibility = View.INVISIBLE
            holder.layout_main.gravity = Gravity.RIGHT
        } else {
            holder.textView_message.setBackgroundColor(R.color.purple_200)
//            holder.textView_message.setBackgroundResource(R.drawable.leftbubble)
            holder.textView_name.text = chat[position].msgUserUID
            holder.textView_name.visibility = View.INVISIBLE
            holder.layout_main.gravity = Gravity.RIGHT
        }
    }



//    inner class RightTypeViewHolder(val binding: ItemRightchatBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(chat: Chat) {
//            binding.rightchatTextviewMsg.text = chat.msg
//            binding.rightchatTextviewTime.text = chat.msgTimeStamp
//        }
//
//    }
//    inner class LeftTypeViewHolder(val binding: ItemLeftchatBinding) : RecyclerView.ViewHolder(binding.root) {
//    }
//
//    override fun onBindViewHolder(holder: MultiRVAdapter.ViewHolder, position: Int) {
//        holder.bind(chat[position])
//        holder.itemView.setOnClickListener{mItemClickListener.onItemClick()}
//    }

    override fun getItemCount(): Int = chat.size


    inner class ChatViewHolder(view : View): RecyclerView.ViewHolder(view){
        val textView_name: TextView= view.findViewById(R.id.chat_textview_name)
        val textView_message: TextView = view.findViewById(R.id.chat_textview_title)
        val textView_time: TextView = view.findViewById(R.id.chat_item_textview_lastmessage)
        val layout_main: LinearLayout = view.findViewById(R.id.chatitem_linearlayout_main)

    }



}