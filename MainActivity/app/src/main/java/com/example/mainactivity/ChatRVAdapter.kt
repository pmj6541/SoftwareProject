package com.example.mainactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainactivity.databinding.ItemClviewBinding

class ChatRVAdapter(private var chatrooms: ArrayList<ChattingRoom>): RecyclerView.Adapter<ChatRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(position : Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ChatRVAdapter.ViewHolder {
        val binding: ItemClviewBinding = ItemClviewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatRVAdapter.ViewHolder, position: Int) {
        holder.bind(chatrooms[position])
        holder.itemView.setOnClickListener{mItemClickListener.onItemClick(position) }

    }

    override fun getItemCount(): Int = chatrooms.size

    inner class ViewHolder(val binding: ItemClviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(chatroom: ChattingRoom){
            binding.titleTv.text = chatroom.title
            binding.fullcountTv.text = chatroom.fullCount.toString()
            binding.curuserTv.text = chatroom.usersUID.count().toString()
            binding.lastchatTv.text = chatroom.msg[chatroom.msg.size-1]
        }

    }
}