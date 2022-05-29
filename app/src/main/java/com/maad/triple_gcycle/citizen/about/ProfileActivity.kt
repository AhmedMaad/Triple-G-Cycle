package com.maad.triple_gcycle.citizen.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.databinding.ActivityProfileBinding
import com.maad.triple_gcycle.factory.User
import com.maad.triple_gcycle.request.Request

class ProfileActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Firebase.firestore

        val prefs = getSharedPreferences("data", MODE_PRIVATE)
        val userId = prefs.getString("id", null)!!

        db.collection("users").document(userId).get().addOnSuccessListener {
            val user = it.toObject(User::class.java)!!
            binding.nameTv.text = "Name: ${user.name}"
            binding.emailTv.text = "Email: ${user.email}"
            binding.phoneNumberTv.text = "Phone number: ${user.number}"
            getRequests(user.id)
        }

    }

    fun getRequests(userId: String) {
        var approvedCounter = 0
        db.collection("requests").get().addOnSuccessListener {
            val requests = it.toObjects(Request::class.java)
            for (request in requests)
                if (request.pointStatus == "Approved" && request.userId == userId)
                    ++approvedCounter
            binding.points.text = (approvedCounter * 20).toString()
        }
    }

}