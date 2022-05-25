package com.example.mainactivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mainactivity.databinding.ActivityChatlistBinding
import com.google.firebase.auth.FirebaseAuth

class ChatlistActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivityChatlistBinding? = null
    private val binding get() = mBinding!!
    private var chatrooms = ArrayList<ChatRoom>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChatlistBinding.inflate(layoutInflater)
        val intent = intent
        val curUser:User = intent.getSerializableExtra("curUser") as User
        Toast.makeText(this,"현재 사용자 : ${curUser.Id}\n현재 사용자 스팟 : ${curUser.location}\n사용자가 선택한 음식 : ${curUser.menu}",Toast.LENGTH_SHORT).show()
        setContentView(binding.root)

        var room: ChattingRoom? = null
        if(intent.hasExtra("myChattingRoom")){
            room = intent.getSerializableExtra("myChattingRoom") as ChattingRoom
        }


        chatrooms.apply {
            add(ChatRoom(R.drawable.human, "디디치킨 먹을 사람", 4))
            add(ChatRoom(R.drawable.human, "리후 주문!!", 1))
            add(ChatRoom(R.drawable.human, "치킨 매니아 같이 시켜요!(가게없음ㅋㅋ)", 2))
            add(ChatRoom(R.drawable.human, "BHC 파티모집", 2))
            if (room != null) {
                add(ChatRoom(R.drawable.human, room.title, room.fullCount))
            }
        }

        val chatRVAdapter = ChatRVAdapter(chatrooms)
        binding.lstUser.adapter = chatRVAdapter
        binding.lstUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        chatRVAdapter.setMyItemClickListener(object: ChatRVAdapter.MyItemClickListener{
            override fun onItemClick() {

            }
        })

    }
}