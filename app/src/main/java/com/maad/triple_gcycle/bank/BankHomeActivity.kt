package com.maad.triple_gcycle.bank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.R
import com.maad.triple_gcycle.databinding.ActivityBankHomeBinding

class BankHomeActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private var pendingTransactions = arrayListOf<String>()
    private var ids = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBankHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        db.collection("bank").get().addOnSuccessListener {
            val allTransactions = it.documents
            for (transaction in allTransactions)
                if (transaction.getString("status") == "Pending") {
                    ids.add(transaction.id)
                    val time = transaction.getString("time")
                    val date = transaction.getString("date")
                    val money = "%.2f".format(transaction.getString("money")!!.toDouble())
                    pendingTransactions.add("Transaction Date: $date\nTransaction Time: $time\nTransferred Money: $money L.E.")
                }

            val adapter = ArrayAdapter(this, R.layout.bank_list_item, pendingTransactions)
            binding.lv.adapter = adapter
            binding.progress.visibility = View.GONE

            if (pendingTransactions.isEmpty())
                Toast.makeText(this, "No Pending Transactions", Toast.LENGTH_SHORT).show()

            binding.lv.setOnItemClickListener { _, _, position, _ ->
                db
                    .collection("bank")
                    .document(ids[position])
                    .update("status", "Approved")
                    .addOnSuccessListener {
                        Toast.makeText(this, "Money sent to App Provider", Toast.LENGTH_LONG).show()
                        recreate()
                    }
            }

        }

    }

}