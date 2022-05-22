package com.maad.triple_gcycle.citizen

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.databinding.ActivityPointsBinding
import com.maad.triple_gcycle.factory.request.Request


class PointsActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPointsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        //check user_id when returning th points
        //show text view with animation

        val prefs = getSharedPreferences("data", MODE_PRIVATE)
        val userId = prefs.getString("id", null)!!

        var approvedCounter = 0
        var rejectedCounter = 0
        var pendingCounter = 0

        db.collection("requests").get().addOnSuccessListener {
            val allRequests = it.toObjects(Request::class.java)
            for (request in allRequests)
                if (request.userId == userId)
                    when (request.pointStatus) {
                        "Approved" -> ++approvedCounter
                        "Rejected" -> ++rejectedCounter
                        "Pending" -> ++pendingCounter
                    }

            val animator = ValueAnimator.ofInt(0, pendingCounter)
            animator.duration = 3000
            animator.addUpdateListener { animation ->
                binding.pendingTv.text = animation.animatedValue.toString()
            }
            animator.start()

            val animator2 = ValueAnimator.ofInt(0, rejectedCounter)
            animator2.duration = 2500
            animator2.addUpdateListener { animation ->
                binding.rejectedTv.text = animation.animatedValue.toString()
            }
            animator2.start()

            val animator3 = ValueAnimator.ofInt(0, approvedCounter)
            animator3.duration = 2000
            animator3.addUpdateListener { animation ->
                binding.approvedTv.text = animation.animatedValue.toString()
            }
            animator3.start()

        }

    }

}