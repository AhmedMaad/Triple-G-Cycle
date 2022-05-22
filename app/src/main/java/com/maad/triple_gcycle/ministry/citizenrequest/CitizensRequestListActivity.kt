package com.maad.triple_gcycle.ministry.citizenrequest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.databinding.ActivityRequestListBinding
import com.maad.triple_gcycle.factory.request.Request
import com.maad.triple_gcycle.ministry.RequestAdapter

class CitizensRequestListActivity : AppCompatActivity(), RequestAdapter.ItemClickListener {

    private lateinit var db: FirebaseFirestore
    private val pendingRequests = arrayListOf<Request>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRequestListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        db.collection("requests").get().addOnSuccessListener {
            val requests = it.toObjects(Request::class.java)
            for (request in requests)
                if (request.pointStatus == "Pending" && request.userType == "Citizen")
                    pendingRequests.add(request)

            val adapter = RequestAdapter(this, pendingRequests, this)
            binding.requestsRv.adapter = adapter
            binding.progress.visibility = View.GONE

        }

    }

    override fun onApproveButtonClick(position: Int) {
        //delete item from list and change pointsstatus to "Approved"
    }

    override fun onRejectBtnClick(position: Int) {
        //delete item from list and change pointsstatus to "Rejected"
    }

    override fun onLocationClick(position: Int) {
        val gmmIntentUri =
            Uri.parse("geo:${pendingRequests[position].lat},${pendingRequests[position].lon}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

}