package com.maad.triple_gcycle.citizen.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}