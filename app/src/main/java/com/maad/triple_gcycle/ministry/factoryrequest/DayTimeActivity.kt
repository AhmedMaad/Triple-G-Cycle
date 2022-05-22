package com.maad.triple_gcycle.ministry.factoryrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityDayTimeBinding

class DayTimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDayTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}