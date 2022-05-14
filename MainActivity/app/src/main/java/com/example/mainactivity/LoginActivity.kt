package com.example.mainactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainactivity.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivityLoginBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        firebaseAuth = Firebase.auth

        setContentView(binding.root)


        var id = binding.editTextTextPersonName.text.toString()
        var passwd = binding.editTextTextPassword.text.toString()
        Toast.makeText(baseContext,"onCreate입니다.",Toast.LENGTH_SHORT)
        //login
        binding.butLogin.setOnClickListener {
            id = binding.editTextTextPersonName.text.toString()
            passwd = binding.editTextTextPassword.text.toString()
            signIn(id,passwd)
        }
        //sign-up
        binding.textView4.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }
    }

    public override fun onStart() {
        super.onStart()
        Toast.makeText(baseContext,"onStart입니다.",Toast.LENGTH_SHORT)
        moveMainPage(firebaseAuth?.currentUser)
    }

    private fun signIn(id: String, password: String) {

        if (id.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth?.signInWithEmailAndPassword(id, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()

                        moveMainPage(firebaseAuth?.currentUser)

                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun moveMainPage(user: FirebaseUser?){
        if( user!= null){
            startActivity(Intent(this,MapActivity::class.java))
            finish()
        }
    }
}