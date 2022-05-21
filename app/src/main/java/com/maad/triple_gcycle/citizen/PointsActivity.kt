package com.maad.triple_gcycle.citizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityPointsBinding

class PointsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPointsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //send "status = pending" by default to any point
        //show text view with animation

    }

}