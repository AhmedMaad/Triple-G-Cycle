package com.maad.triple_gcycle.citizen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.citizen.offer.OffersActivity
import com.maad.triple_gcycle.databinding.ActivityCitizenHomeBinding
import com.maad.triple_gcycle.factory.RequestsActivity

class CitizenHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCitizenHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.requestCv.setOnClickListener {
            startActivity(Intent(this, RequestsActivity::class.java))
        }

        binding.pointsCv.setOnClickListener {
            startActivity(Intent(this, PointsActivity::class.java))
        }

        binding.offersCv.setOnClickListener {
            startActivity(Intent(this, OffersActivity::class.java))
        }

    }


}