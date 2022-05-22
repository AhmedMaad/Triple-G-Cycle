package com.maad.triple_gcycle.ministry.factoryrequest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.databinding.ActivityRequestListBinding
import com.maad.triple_gcycle.factory.FactoryRequest
import com.maad.triple_gcycle.request.Request
import com.maad.triple_gcycle.ministry.RequestAdapter

class FactoriesRequestListActivity : AppCompatActivity(), RequestAdapter.ItemClickListener {

    private lateinit var db: FirebaseFirestore
    private val pendingRequests = arrayListOf<Request>()
    private lateinit var binding: ActivityRequestListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore
    }

    override fun onResume() {
        super.onResume()
        pendingRequests.clear()
        db.collection("requests").get().addOnSuccessListener {
            val requests = it.toObjects(FactoryRequest::class.java)
            for (request in requests)
                if (request.pointStatus == "Pending" && request.userType == "Factory")
                    pendingRequests.add(request)

            val adapter = RequestAdapter(this, pendingRequests, this)
            binding.requestsRv.adapter = adapter
            binding.progress.visibility = View.GONE
        }
    }

    override fun onApproveButtonClick(position: Int) {

    }

    override fun onRejectBtnClick(position: Int) {

    }

    override fun onLocationClick(position: Int) {
        val gmmIntentUri =
            Uri.parse("geo:${pendingRequests[position].lat},${pendingRequests[position].lon}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

}