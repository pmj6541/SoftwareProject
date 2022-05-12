package com.example.mainactivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainactivity.databinding.ActivityMainBinding
import com.example.mainactivity.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivitySignupBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignupBinding.inflate(layoutInflater)
        firebaseAuth = Firebase.auth

        var email = binding.editTextEmail.text.toString()
        var name = binding.editTextName.text.toString()
        var passwd = binding.editTextPassword.text.toString()

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
            Toast.makeText(
                this, "일단 실행함.",
                Toast.LENGTH_SHORT
            ).show()
            firebaseAuth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_SHORT
                        ).show()
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
}