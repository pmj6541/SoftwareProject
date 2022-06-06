package com.example.mainactivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainactivity.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivitySignupBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignupBinding.inflate(layoutInflater)
        firebaseAuth = Firebase.auth

        var email = ""
        var name = ""
        var passwd = ""

        setContentView(binding.root)
        binding.signupBtn.setOnClickListener {
            email = binding.editTextEmail.text.toString()
            name = binding.editTextName.text.toString()
            passwd = binding.editTextPassword.text.toString()
            createAccount(email, name, passwd)
        }


    }

    private fun createAccount(email: String, name: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val userInfo = DBUser(email, name, password)
                        addUserOnFirebase(userInfo)
                        finish() // 가입창 종료
                    } else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun addUserOnFirebase(userInfo : DBUser) {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference()
        ref.child("users/${firebaseAuth?.uid.toString()}").setValue(userInfo)
    }

}