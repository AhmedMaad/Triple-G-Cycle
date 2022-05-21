package com.maad.triple_gcycle.factory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityFactoryHomeBinding

class FactoryHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFactoryHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.requestCv.setOnClickListener {  }

        binding.ministryCv.setOnClickListener {  }

    }
}