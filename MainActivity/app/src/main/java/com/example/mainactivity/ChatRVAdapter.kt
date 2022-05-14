package com.example.mainactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainactivity.databinding.ItemViewBinding

class ChatRVAdapter(private var chatrooms: ArrayList<ChatRoom>): RecyclerView.Adapter<ChatRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick()
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ChatRVAdapter.ViewHolder {
        val binding: ItemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatRVAdapter.ViewHolder, position: Int) {
        holder.bind(chatrooms[position])
        holder.itemView.setOnClickListener{mItemClickListener.onItemClick() }

    }

    override fun getItemCount(): Int = chatrooms.size

    inner class ViewHolder(val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(chatroom: ChatRoom){
            binding.txtUserId.text = chatroom.title
            binding.txtUserName.text = chatroom.headcount.toString()
            binding.imgUserIcon.setImageResource(chatroom.roomimg!!)
        }

    }
}