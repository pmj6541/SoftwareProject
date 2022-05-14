package com.example.mainactivity

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.mainactivity.databinding.ActivityCreatechatBinding
import com.google.firebase.auth.FirebaseAuth

class CreatechatActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    lateinit var binding: ActivityCreatechatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatechatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val foods = listOf("음식 카테고리를 선택해주세요.", "치킨", "피자", "햄버거", "한식", "중식", "일식", "카페,디저트", "분식", "족발")
        val peopleCnt = listOf("인원수를 선택해주세요.", "2명", "3명", "4명", "5명")

        val adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, foods)
        val adapter2 = ArrayAdapter<String>(this, R.layout.simple_spinner_item, peopleCnt)

        binding.spinner.adapter = adapter
        binding.spinner2.adapter = adapter2

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ){
                if(position == 1){
                    binding.imageView.setImageResource(com.example.mainactivity.R.drawable.chicken)
                }else if (position == 2){
                    binding.imageView.setImageResource(com.example.mainactivity.R.drawable.pizza)
                }else if (position == 3){
                    binding.imageView.setImageResource(com.example.mainactivity.R.drawable.hamburger)
                }else if (position == 4){
                    binding.imageView.setImageResource(com.example.mainactivity.R.drawable.korean)
                }else if (position == 5){
                    binding.imageView.setImageResource(com.example.mainactivity.R.drawable.chinese)
                }else if (position == 6){
                    binding.imageView.setImageResource(com.example.mainactivity.R.drawable.japanese)
                }else if (position == 7){
                    binding.imageView.setImageResource(com.example.mainactivity.R.drawable.dessert)
                }else if (position == 8){
                    binding.imageView.setImageResource(com.example.mainactivity.R.drawable.snack)
                }else if (position == 9){
                    binding.imageView.setImageResource(com.example.mainactivity.R.drawable.pigleg)
                }

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ){
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.button.setOnClickListener{
            var curUser:User = intent.getSerializableExtra("curUser") as User
            val myChattingRoom : ChattingRoom = setChattingroomInfo(binding.spinner.selectedItem.toString(),
                curUser.location,
                binding.editTextTextPersonName2.text.toString(),
                binding.spinner2.selectedItem.toString().split("명")[0].toInt()
            )
            curUser = getfoodInfo(curUser,binding.spinner.selectedItem.toString())

            val intent : Intent = Intent(this,ChatlistActivity::class.java)
            intent.putExtra("curUser",curUser)
            intent.putExtra("myChattingRoom",myChattingRoom)
            getCreatedChattingRoom(myChattingRoom)
            startActivity(intent)
        }
    }


    private fun setChattingroomInfo(menu: String, location : String, name: String, fullCount: Int): ChattingRoom {
        return ChattingRoom(
            menu,
            location,
            name,
            fullCount
        )
    }

    private fun getCreatedChattingRoom(chattingRoom : ChattingRoom){
        Toast.makeText(this,"메뉴 : ${chattingRoom.menu}\n" +
                "스팟 : ${chattingRoom.location}\n" +
                "이름 : ${chattingRoom.name}\n" +
                "인원수 : ${chattingRoom.fullCount}명",Toast.LENGTH_SHORT).show()
    }

    private fun getfoodInfo(curUser : User, food: String): User {

        return User(
            curUser.Id,
            curUser.location,
            food
        )
    }

}