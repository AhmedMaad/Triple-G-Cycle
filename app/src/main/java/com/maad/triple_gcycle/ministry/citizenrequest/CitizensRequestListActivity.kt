package com.maad.triple_gcycle.ministry.citizenrequest

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
import java.util.*
import kotlin.collections.HashMap
import kotlin.random.Random

class CitizensRequestListActivity : AppCompatActivity(), RequestAdapter.ItemClickListener {

    private lateinit var db: FirebaseFirestore
    private val pendingRequests = arrayListOf<Request>()
    private lateinit var adapter: RequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRequestListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        db.collection("requests").get().addOnSuccessListener {
            val requests = it.toObjects(Request::class.java)
            for (request in requests)
                if (request.pointStatus == "Pending" && request.userType == "Citizen"
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
        db.collection("requests").document(pendingRequests[position].requestId)
            .update("pointStatus", "Approved").addOnSuccessListener {
                sendMoney(position)
            }
    }

    private fun sendMoney(position: Int) {

        val now = Calendar.getInstance()
        val y = now.get(Calendar.YEAR)
        val m = now.get(Calendar.MONTH) + 1
        val d = now.get(Calendar.DAY_OF_MONTH)
        val h = now.get(Calendar.HOUR_OF_DAY)
        val min = now.get(Calendar.MINUTE)
        val s = now.get(Calendar.SECOND)
        val ms = now.get(Calendar.MILLISECOND)
        val time = "Transaction Time: $d-$m-$y $h:$min:$s.$ms"

        val data = HashMap<String, String>()
        data["money"] = Random.nextDouble(200.00, 1000.00).toString() //until 999.9999
        data["time"] = time
        data["status"] = "Pending"

        db.collection("bank").add(data).addOnSuccessListener {
            pendingRequests.removeAt(position)
            adapter.notifyItemRemoved(position)
            Toast.makeText(this, "Request approved, and money sent to bank", Toast.LENGTH_SHORT)
                .show()
        }

    }

    override fun onRejectBtnClick(position: Int) {
        db.collection("requests").document(pendingRequests[position].requestId)
            .update("pointStatus", "Rejected").addOnSuccessListener {
                pendingRequests.removeAt(position)
                adapter.notifyItemRemoved(position)
                Toast.makeText(this, "Request Rejected", Toast.LENGTH_SHORT).show()
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