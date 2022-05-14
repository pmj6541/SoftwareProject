package com.example.mainactivity

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.mainactivity.databinding.ActivityCreatechatBinding
import com.google.firebase.auth.FirebaseAuth

class CreatechatActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    lateinit var binding: ActivityCreatechatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatechatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var foods = listOf("음식 카테고리를 선택해주세요.", "치킨", "피자", "햄버거", "한식", "중식", "일식", "카페,디저트", "분식", "족발")
        var peopleCnt = listOf("인원수를 선택해주세요.", "2명", "3명", "4명", "5명")

        var adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, foods)
        var adapter2 = ArrayAdapter<String>(this, R.layout.simple_spinner_item, peopleCnt)

        binding.spinner.adapter = adapter
        binding.spinner2.adapter = adapter2

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
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
            ) {
                
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }




}