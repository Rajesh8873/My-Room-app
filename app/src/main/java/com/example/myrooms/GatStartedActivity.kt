package com.example.myrooms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrooms.databinding.ActivityGatStartedBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GatStartedActivity : AppCompatActivity() {
    private var binding:ActivityGatStartedBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGatStartedBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.cvGetStarted?.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }
       // logout code with firebase //
//        val auth = Firebase.auth
//        if (auth.currentUser!=null){
//            startActivity(Intent(this,MainActivity::class.java))
//            finish()
//        }
    }
}