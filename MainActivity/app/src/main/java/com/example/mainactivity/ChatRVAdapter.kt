package com.example.mainactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainactivity.databinding.ItemClviewBinding
import com.example.mainactivity.databinding.ItemViewBinding

class ChatRVAdapter(private var chatrooms: ArrayList<ChattingRoom>): RecyclerView.Adapter<ChatRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick()
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
        holder.itemView.setOnClickListener{mItemClickListener.onItemClick() }

    }

    override fun getItemCount(): Int = chatrooms.size

    inner class ViewHolder(val binding: ItemClviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(chatroom: ChattingRoom){
            binding.titleTv.text = chatroom.title
            binding.fullcountTv.text = chatroom.fullCount.toString()
            binding.locationTv.text = chatroom.location
            binding.curuserTv.text = chatroom.usersUID.count().toString()
            binding.menuTv.text = chatroom.menu
            binding.lastchatTv.text = chatroom.msg[0]
        }

    }
}