package com.maad.triple_gcycle.factory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.R
import com.maad.triple_gcycle.databinding.ActivityComingMinistryListBinding
import com.maad.triple_gcycle.request.Request

class ComingMinistryListActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityComingMinistryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        val prefs = getSharedPreferences("data", MODE_PRIVATE)
        val userId = prefs.getString("id", null)!!

        val approvedRequests = arrayListOf<String>()

        db.collection("requests").get().addOnSuccessListener {
            val allRequests = it.toObjects(Request::class.java)
            for (request in allRequests)
                if (request.pointStatus == "Approved" && request.userId == userId)
                    approvedRequests.add("${request.destination} is coming on ${request.day} at ${request.time}")

            val adapter = ArrayAdapter(this, R.layout.coming_list_item, approvedRequests)
            binding.lv.adapter = adapter
            binding.progress.visibility = View.GONE

            if (approvedRequests.isEmpty())
                Toast.makeText(this, "No one is coming at the moment", Toast.LENGTH_SHORT).show();

        }

    }

}