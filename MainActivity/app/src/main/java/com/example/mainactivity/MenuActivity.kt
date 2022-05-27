package com.example.mainactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainactivity.databinding.ActivityMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MenuActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivityMenuBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        var curUser:User = intent.getSerializableExtra("curUser") as User
        Toast.makeText(this, "현재 사용자 = ${curUser.Id} \n현재 사용자 스팟 : ${curUser.location}",Toast.LENGTH_SHORT).show()

        binding.location.text = curUser.location

        binding.imageView00.setOnClickListener {
            curUser = setFoodInfo(curUser, "치킨")
            goNextActivity(curUser)
        }
        binding.imageView01.setOnClickListener {
            curUser = setFoodInfo(curUser, "피자")
            goNextActivity(curUser)
        }
        binding.imageView02.setOnClickListener {
            curUser = setFoodInfo(curUser, "햄버거")
            goNextActivity(curUser)
        }
        binding.imageView03.setOnClickListener {
            curUser = setFoodInfo(curUser, "한식")
            goNextActivity(curUser)
        }
        binding.imageView04.setOnClickListener {
            curUser = setFoodInfo(curUser, "중식")
            goNextActivity(curUser)
        }
        binding.imageView05.setOnClickListener {
            curUser = setFoodInfo(curUser, "일식")
            goNextActivity(curUser)
        }
        binding.imageView06.setOnClickListener {
            curUser = setFoodInfo(curUser, "카페, 디저트")
            goNextActivity(curUser)
        }
        binding.imageView07.setOnClickListener {
            curUser = setFoodInfo(curUser, "분식")
            goNextActivity(curUser)
        }
        binding.imageView08.setOnClickListener {
            curUser = setFoodInfo(curUser, "족발")
            goNextActivity(curUser)
        }
        binding.createchatbtn.setOnClickListener{
            val intent : Intent = Intent(this,CreatechatActivity::class.java)
            intent.putExtra("curUser",curUser)
            startActivity(intent)
        }
        binding.backbtn.setOnClickListener{
            finish()
        }

    }
    private fun setFoodInfo(curUser : User, food: String): User {
        return User(
            curUser.Id,
            curUser.location,
            food
        )
    }

    private fun goNextActivity(curUser : User){
        val intent : Intent = Intent(this,ChatlistActivity::class.java)
        intent.putExtra("curUser",curUser)
        startActivity(intent)
    }
}