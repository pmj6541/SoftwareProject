package com.example.mainactivity

import android.util.Log
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

class MultiRVAdapter(private var chat: ArrayList<Chat>): RecyclerView.Adapter<MultiRVAdapter.ViewHolder>() {
    private var firebaseAuth : FirebaseAuth? = null

    interface MyItemClickListener{
        fun onItemClick()
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MultiRVAdapter.ViewHolder {
        val view: View?
        firebaseAuth = Firebase.auth
        val binding: ItemRightchatBinding = ItemRightchatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
//        if(firebaseAuth?.currentUser?.uid.toString() == chat[0].msgUserUID) {
//            val binding: ItemRightchatBinding = ItemRightchatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
//            return ViewHolder(binding)
//        } else {
//            val binding: ItemRightchatBinding = ItemRightchatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
//            return ViewHolder(binding)
//        }
    }


//    fun onBindViewHolder(holder: MultiRVAdapter.RightTypeViewHolder, position: Int) {
//        firebaseAuth = Firebase.auth
//        if(firebaseAuth?.currentUser?.uid.toString() == chat[0].msgUserUID) {
//            holder.bind(chat[position])
//            holder.itemView.setOnClickListener{mItemClickListener.onItemClick()}
//        } else {
//            holder.bind(chat[position])
//            holder.itemView.setOnClickListener{mItemClickListener.onItemClick()}
//        }
//    }



    inner class RightTypeViewHolder(val binding: ItemRightchatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            binding.rightchatTextviewMsg.text = chat.msg
            binding.rightchatTextviewTime.text = chat.msgTimeStamp
        }

    }
    inner class LeftTypeViewHolder(val binding: ItemLeftchatBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onBindViewHolder(holder: MultiRVAdapter.ViewHolder, position: Int) {
        holder.bind(chat[position])
        holder.itemView.setOnClickListener{mItemClickListener.onItemClick()}
    }

    override fun getItemCount(): Int = chat.size


    inner class ViewHolder(val binding: ItemRightchatBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(chat: Chat) {
            binding.rightchatTextviewMsg.text = chat.msg
            binding.rightchatTextviewTime.text = chat.msgTimeStamp
        }
    }



}