package com.example.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.example.mainactivity.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        firebaseAuth = Firebase.auth
        setContentView(binding.root)
        startActivity(Intent(this,LoginActivity::class.java))

        binding.startBtn.setOnClickListener {
            moveMainPage(firebaseAuth?.currentUser)
        }



    }

    private fun moveMainPage(user: FirebaseUser?){
        if( user!= null){
            startActivity(Intent(this,MapActivity::class.java))
        }else{
            startActivity(Intent(this,LoginActivity::class.java))

        }
    }


    override fun onDestroy() { //
        mBinding = null
        FirebaseAuth.getInstance().signOut()
        super.onDestroy()
    }

}