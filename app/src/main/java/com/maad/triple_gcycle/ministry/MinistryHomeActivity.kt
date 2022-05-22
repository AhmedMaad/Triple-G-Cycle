package com.maad.triple_gcycle.ministry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityMinistryHomeBinding

class MinistryHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMinistryHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userTypeTv.text =
            "Ministry of ${getSharedPreferences("data", MODE_PRIVATE).getString("type", null)}"

        binding.citizenRequestCv.setOnClickListener {
            startActivity(Intent(this, CitizensRequestListActivity::class.java))
        }

        binding.factoryRequestCv.setOnClickListener {
            startActivity(Intent(this, FactoriesRequestListActivity::class.java))
        }

    }
}