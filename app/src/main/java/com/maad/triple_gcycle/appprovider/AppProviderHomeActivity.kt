package com.maad.triple_gcycle.appprovider

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.databinding.ActivityAppProviderHomeBinding

class AppProviderHomeActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private var accountBalance = 0.0
    private lateinit var binding: ActivityAppProviderHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppProviderHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        db.collection("bank").get().addOnSuccessListener {
            val allTransactions = it.documents
            for (transaction in allTransactions)
                if (transaction.getString("status") == "Approved") {
                    val money =
                        ("%.2f".format(transaction.getString("money")!!.toDouble())).toDouble()
                    accountBalance += money
                }

            binding.approvedTv.text = accountBalance.toString()
        }

        binding.currencySwitch.setOnCheckedChangeListener { _, isChecked ->
            accountBalance =
                when (isChecked) {
                    true -> accountBalance * 18
                    false -> accountBalance / 18
                }
            binding.approvedTv.text = ("%.2f".format(accountBalance).toDouble()).toString()
        }

    }

}