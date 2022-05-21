package com.maad.triple_gcycle.ministry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityMinistryHomeBinding

class MinistryHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMinistryHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}