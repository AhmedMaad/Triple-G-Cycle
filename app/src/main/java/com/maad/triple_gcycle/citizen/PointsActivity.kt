package com.maad.triple_gcycle.citizen

import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.databinding.ActivityPointsBinding
import com.maad.triple_gcycle.request.Request


class PointsActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPointsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

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

            textAnimation(approvedCounter, 2000, binding.approvedTv)
            textAnimation(rejectedCounter, 2500, binding.rejectedTv)
            textAnimation(pendingCounter, 3000, binding.pendingTv)

        }

    }

    fun textAnimation(counter: Int, duration: Int, text: TextView) {
        val animator = ValueAnimator.ofInt(0, counter)
        animator.duration = duration.toLong()
        animator.addUpdateListener { animation -> text.text = animation.animatedValue.toString() }
        animator.start()
    }


}