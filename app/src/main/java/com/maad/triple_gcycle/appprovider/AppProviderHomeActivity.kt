package com.maad.triple_gcycle.appprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityAppProviderHomeBinding

class AppProviderHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppProviderHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}