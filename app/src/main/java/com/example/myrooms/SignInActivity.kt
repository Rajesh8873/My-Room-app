package com.example.myrooms

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.example.myrooms.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : BaseActivity() {
    private var binding:ActivitySignInBinding? = null
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth

        binding?.tvRegister?.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

        binding?.tvForgotPassword?.setOnClickListener {
            startActivity(Intent(this,ForgetPasswordActivity::class.java))
        }

        binding?.btnSignIn?.setOnClickListener{
            sinInUser()
        }
    }

    private fun sinInUser(){
        val email = binding?.etSinInEmail?.text.toString()
        val password = binding?.etSinInPassword?.text.toString()
        if (validateForm(email, password))
        {
            showProgressBar()
         auth.signInWithEmailAndPassword(email, password)
             .addOnCompleteListener{
                 task->
                 if(task.isSuccessful)
                 {
                     startActivity(Intent(this,MainActivity::class.java))
                     finish()
                 }else{
                     showToast(this,"can't login currently. try after Sometime")
                 }
             }
        }
    }

    private fun validateForm(email: String, password: String): Boolean {
        return when {

            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding?.tilEmail?.error = "Enter valid email address"
                false
            }

            TextUtils.isEmpty(password) -> {
                binding?.tilPassword?.error = "Enter password"
                false
            }else -> {true}
        }

    }
    }
