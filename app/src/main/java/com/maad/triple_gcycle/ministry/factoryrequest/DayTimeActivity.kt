package com.maad.triple_gcycle.ministry.factoryrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.databinding.ActivityDayTimeBinding
import com.maad.triple_gcycle.factory.FactoryRequest
import com.maad.triple_gcycle.request.Request
import kotlin.random.Random

class DayTimeActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDayTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        binding.dayCv.setOnClickListener {

        }

        binding.timeCv.setOnClickListener {

        }

        binding.approveBtn.setOnClickListener {
            if (binding.dayTv.text == "Choose Day" || binding.timeTv.text == "Choose Time")
                Toast.makeText(this, "Choose both day & time", Toast.LENGTH_SHORT).show()
            else {
                val request = intent.getParcelableExtra<FactoryRequest>("request")!!
                request.pointStatus = "Approved"
                // request.day =
                //  request.time =
                db.collection("requests").document(request.requestId)
                    .set(request).addOnSuccessListener {
                        sendMoney()
                    }
            }
        }

    }

    private fun sendMoney() {
        val data = HashMap<String, Double>()
        data["money"] = Random.nextDouble(1000.00, 2000.00) //until 1999.9999
        db.collection("bank").add(data).addOnSuccessListener {
            Toast.makeText(this, "Request approved, and money sent to bank", Toast.LENGTH_SHORT)
                .show()
        }

    }

}