package com.maad.triple_gcycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

    }
}