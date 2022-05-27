package com.example.mainactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainactivity.databinding.ItemClviewBinding
import com.example.mainactivity.databinding.ItemLeftchatBinding
import com.example.mainactivity.databinding.ItemRightchatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MultiRVAdapter(private var chatroom: ChattingRoom): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var firebaseAuth : FirebaseAuth? = null
    val list = chatroom.msg

    interface MyItemClickListener{
        fun onItemClick()
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MultiRVAdapter.RightTypeViewHolder {
        val view: View?
        firebaseAuth = Firebase.auth
        if(firebaseAuth?.currentUser?.uid.toString() == chatroom.msgUserUID[0]) {
            val binding: ItemRightchatBinding = ItemRightchatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return RightTypeViewHolder(binding)
        } else {
            val binding: ItemRightchatBinding = ItemRightchatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return RightTypeViewHolder(binding)
        }
    }

    fun onBindViewHolder(holder: MultiRVAdapter.RightTypeViewHolder, position: Int) {
        firebaseAuth = Firebase.auth
        if(firebaseAuth?.currentUser?.uid.toString() == chatroom.msgUserUID[0]) {
            holder.bind(chatroom)
            holder.itemView.setOnClickListener{mItemClickListener.onItemClick()}

        } else {

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class RightTypeViewHolder(val binding: ItemRightchatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatroom: ChattingRoom) {
            binding.rightchatTextviewMsg.text = chatroom.msg[0]
            binding.rightchatTextviewTime.text = chatroom.msgTimeStamp[0]
        }

    }
    inner class LeftTypeViewHolder(val binding: ItemLeftchatBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}