package com.maad.triple_gcycle.ministry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityMinistryHomeBinding
import com.maad.triple_gcycle.ministry.citizenrequest.CitizensRequestListActivity
import com.maad.triple_gcycle.ministry.factoryrequest.FactoriesRequestListActivity

class MinistryHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMinistryHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userTypeTv.text =
            "Ministry of ${getSharedPreferences("data", MODE_PRIVATE).getString("type", null)}"

        binding.citizenRequestCv.setOnClickListener {
            val i = Intent(this, CitizensRequestListActivity::class.java)
            i.putExtra("ministry", intent.getStringExtra("ministry"))
            startActivity(i)
        }

        binding.factoryRequestCv.setOnClickListener {
            val i = Intent(this, FactoriesRequestListActivity::class.java)
            i.putExtra("ministry", intent.getStringExtra("ministry"))
            startActivity(i)
        }

    }
}