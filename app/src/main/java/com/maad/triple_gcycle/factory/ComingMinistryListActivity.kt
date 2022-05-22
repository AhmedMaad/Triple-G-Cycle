package com.maad.triple_gcycle.factory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityComingMinistryListBinding

class ComingMinistryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityComingMinistryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //return "Approved" request and use "user_id"
        //show in recycler view like that:
        // "Ministry of health is coming on 19/5/2022 at 19:00 p.m."

    }
}