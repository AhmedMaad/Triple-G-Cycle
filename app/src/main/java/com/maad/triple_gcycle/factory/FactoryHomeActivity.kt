package com.maad.triple_gcycle.factory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityFactoryHomeBinding
import com.maad.triple_gcycle.request.RequestsActivity

class FactoryHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFactoryHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.requestCv.setOnClickListener {
            startActivity(Intent(this, RequestsActivity::class.java))
        }

        binding.ministryCv.setOnClickListener {
            startActivity(Intent(this, ComingMinistryListActivity::class.java))
        }

    }
}