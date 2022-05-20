package com.maad.triple_gcycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}