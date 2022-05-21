package com.maad.triple_gcycle.factory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityRequestsBinding

class RequestsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRequestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}