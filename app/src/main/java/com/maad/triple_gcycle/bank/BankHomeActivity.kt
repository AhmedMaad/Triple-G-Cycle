package com.maad.triple_gcycle.bank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.databinding.ActivityBankHomeBinding

class BankHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBankHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}