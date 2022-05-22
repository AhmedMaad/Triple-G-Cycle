package com.maad.triple_gcycle.request

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.maad.triple_gcycle.databinding.ActivityRequestsBinding
import com.maad.triple_gcycle.factory.FactoryRequest
import java.util.*

class RequestsActivity : AppCompatActivity() {

    private var imageUri: Uri? = null
    private lateinit var binding: ActivityRequestsBinding
    private var lat = 0.0
    private var lon = 0.0
    private lateinit var storage: FirebaseStorage
    private lateinit var db: FirebaseFirestore
    private val requestDialog = RequestDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore
        storage = Firebase.storage

        binding.ivContainer.setOnClickListener {
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/*"
            startActivityForResult(i, 200)
        }

        binding.locationTv.setOnClickListener {
            val i = Intent(this, MapsActivity::class.java)
            startActivityForResult(i, 99)
        }

        binding.submitRequestBtn.setOnClickListener {
            if (lat == 0.0 || lon == 0.0 || imageUri == null)
                Toast.makeText(this, "Please add problem details", Toast.LENGTH_SHORT).show()
            else
                uploadImage()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 200) {
            imageUri = data?.data
            binding.ivContainer.setImageURI(imageUri)
            binding.galleryIconIv.visibility = View.INVISIBLE
        } else if (resultCode == RESULT_OK && requestCode == 99) {
            lat = data!!.getDoubleExtra("lat", 0.0)
            lon = data.getDoubleExtra("lon", 0.0)
            binding.locationTv.text = "${"%.4f".format(lat)}, ${"%.4f".format(lon)}"
        }

    }

    private fun uploadImage() {
        requestDialog.show(supportFragmentManager, null)
        requestDialog.isCancelable = false
        val now = Calendar.getInstance()
        val y: Int = now.get(Calendar.YEAR)
        val m: Int = now.get(Calendar.MONTH) + 1

        val d: Int = now.get(Calendar.DAY_OF_MONTH)
        val h: Int = now.get(Calendar.HOUR_OF_DAY)
        val min: Int = now.get(Calendar.MINUTE)
        val s: Int = now.get(Calendar.SECOND)
        val imageName = "image: $d-$m-$y $h:$min:$s"

        val storageRef = storage.reference.child(imageName)
        storageRef.putFile(imageUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { submitRequest(it!!) }
            }
    }

    private fun submitRequest(picture: Uri) {
        val prefs = getSharedPreferences("data", MODE_PRIVATE)
        val userId = prefs.getString("id", null)!!
        val userType = prefs.getString("type", null)!!

        val details = binding.problemDetailsEt.text.toString()
        val destinationRB: RadioButton = findViewById(binding.destinationGroup.checkedRadioButtonId)
        val destination = destinationRB.text.toString()

        val request = when (userType) {
            "Citizen" -> Request(
                userId,
                userType,
                picture.toString(),
                lat,
                lon,
                details,
                destination
            )
            else -> FactoryRequest(
                userId,
                userType,
                picture.toString(),
                lat,
                lon,
                details,
                destination
            )
        }

        db.collection("requests").add(request).addOnSuccessListener {
            it.update("requestId", it.id).addOnSuccessListener {
                Toast.makeText(this, "Thanks for saving earth", Toast.LENGTH_SHORT).show()
                requestDialog.dismiss()
                finish()
            }
        }


    }

}