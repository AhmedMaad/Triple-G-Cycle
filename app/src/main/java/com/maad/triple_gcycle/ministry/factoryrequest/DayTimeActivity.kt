package com.maad.triple_gcycle.ministry.factoryrequest

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.databinding.ActivityDayTimeBinding
import com.maad.triple_gcycle.request.Request
import java.util.*
import kotlin.collections.HashMap
import kotlin.random.Random

class DayTimeActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var db: FirebaseFirestore
    private var day = ""
    private var time = ""
    private lateinit var binding: ActivityDayTimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        binding.dayCv.setOnClickListener {
            val dayDialog = DayPickerFragment()
            dayDialog.isCancelable = false
            dayDialog.show(supportFragmentManager, null)
        }

        binding.timeCv.setOnClickListener {
            val timeDialog = TimePickerFragment()
            timeDialog.isCancelable = false
            timeDialog.show(supportFragmentManager, null)
        }

        binding.approveBtn.setOnClickListener {
            if (binding.dayTv.text == "Choose Day" || binding.timeTv.text == "Choose Time")
                Toast.makeText(this, "Choose both day & time", Toast.LENGTH_SHORT).show()
            else {
                val request = intent.getParcelableExtra<Request>("request")!!
                request.pointStatus = "Approved"
                request.day = day
                request.time = time
                db.collection("requests").document(request.requestId)
                    .set(request).addOnSuccessListener {
                        sendMoney()
                    }
            }
        }

    }

    private fun sendMoney() {
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
        data["money"] = Random.nextDouble(1000.00, 2000.00).toString() //until 1999.9999
        data["time"] = time
        data["status"] = "Pending"

        db.collection("bank").add(data).addOnSuccessListener {
            Toast.makeText(this, "Request approved, and money sent to bank", Toast.LENGTH_SHORT)
                .show()
            finish()
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        day = "$year/${month + 1}/$dayOfMonth"
        binding.dayTv.text = "Choose Day\n$day"
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        time =
            if (hourOfDay < 10 && minute == 0) "0$hourOfDay:${minute}0"
            else if (hourOfDay < 10) "0$hourOfDay:${minute}"
            else if (minute == 0) "$hourOfDay:${minute}0"
            else "$hourOfDay:${minute}"
        binding.timeTv.text = "Choose Time\n$time"
    }

}