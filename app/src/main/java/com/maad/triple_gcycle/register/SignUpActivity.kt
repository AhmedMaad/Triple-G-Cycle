package com.maad.triple_gcycle.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.R
import com.maad.triple_gcycle.databinding.ActivitySignUpBinding
import com.maad.triple_gcycle.factory.User
import com.maad.triple_gcycle.citizen.CitizenHomeActivity
import com.maad.triple_gcycle.factory.FactoryHomeActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    var userId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        db = Firebase.firestore

        binding.signInTv.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        binding.userTypeGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.factory_rb){
                binding.typeTv.visibility = View.VISIBLE
                binding.typeEt.visibility = View.VISIBLE
            }
            else{
                binding.typeTv.visibility = View.INVISIBLE
                binding.typeEt.visibility = View.INVISIBLE
            }
        }

        binding.signUpBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passwordEt.text.toString()
            val confirmPass = binding.confirmPasswordEt.text.toString()
            val name = binding.nameEt.text.toString()
            val phoneNo = binding.phoneEt.text.toString()
            val typeRB: RadioButton = findViewById(binding.userTypeGroup.checkedRadioButtonId)
            val type = typeRB.text.toString()
            if (pass != confirmPass || pass.length < 6)
                Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
            else
                auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            userId = task.result.user!!.uid
                            val prefs = getSharedPreferences("data", MODE_PRIVATE).edit()
                            prefs.putString("id", userId)
                            prefs.putString("type", type)
                            prefs.apply()
                            val user = User(userId, email, type, name, phoneNo)
                            db.collection("users").document(userId).set(user).addOnSuccessListener {
                                Toast.makeText(this, "Welcome to the community!", Toast.LENGTH_LONG)
                                    .show()
                                when (type) {
                                    "Citizen" -> {
                                        startActivity(Intent(this, CitizenHomeActivity::class.java))
                                        finishAffinity()
                                    }
                                    "Factory" -> {
                                        startActivity(Intent(this, FactoryHomeActivity::class.java))
                                        finishAffinity()
                                    }
                                }


                            }

                        } else
                            Toast.makeText(this, "Error Adding User", Toast.LENGTH_SHORT).show()
                    }
        }

    }

}