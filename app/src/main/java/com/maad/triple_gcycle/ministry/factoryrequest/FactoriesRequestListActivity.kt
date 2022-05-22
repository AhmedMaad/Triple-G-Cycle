package com.maad.triple_gcycle.ministry.factoryrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityRequestListBinding

class FactoriesRequestListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRequestListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hide progress bar after receiving data that has status "pending"
        //check status "pending" and "request type" is for userType

    }
}