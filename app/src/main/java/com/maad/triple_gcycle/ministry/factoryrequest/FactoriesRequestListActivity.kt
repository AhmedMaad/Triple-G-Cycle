package com.maad.triple_gcycle.ministry.factoryrequest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.databinding.ActivityRequestListBinding
import com.maad.triple_gcycle.request.Request
import com.maad.triple_gcycle.ministry.RequestAdapter

class FactoriesRequestListActivity : AppCompatActivity(), RequestAdapter.ItemClickListener {

    private lateinit var db: FirebaseFirestore
    private val pendingRequests = arrayListOf<Request>()
    private lateinit var binding: ActivityRequestListBinding
    private lateinit var adapter: RequestAdapter

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
            val requests = it.toObjects(Request::class.java)
            for (request in requests)
                if (request.pointStatus == "Pending" && request.userType == "Factory"
                    && request.destination == "Ministry of ${intent.getStringExtra("ministry")}"
                )
                    pendingRequests.add(request)

            adapter = RequestAdapter(this, pendingRequests, this)
            binding.requestsRv.adapter = adapter
            binding.progress.visibility = View.GONE

            if (pendingRequests.isEmpty())
                Toast.makeText(this, "No pending requests", Toast.LENGTH_SHORT).show();

        }
    }

    override fun onApproveButtonClick(position: Int) {
        val i = Intent(this, DayTimeActivity::class.java)
        i.putExtra("request", pendingRequests[position])
        startActivity(i)
    }

    override fun onRejectBtnClick(position: Int) {
        db.collection("requests").document(pendingRequests[position].requestId)
            .update("pointStatus", "Rejected").addOnSuccessListener {
                pendingRequests.removeAt(position)
                adapter.notifyItemRemoved(position)
                Toast.makeText(this, "Request Rejected", Toast.LENGTH_SHORT).show();
            }
    }

    override fun onLocationClick(position: Int) {
        val gmmIntentUri =
            Uri.parse("geo:${pendingRequests[position].lat},${pendingRequests[position].lon}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

}