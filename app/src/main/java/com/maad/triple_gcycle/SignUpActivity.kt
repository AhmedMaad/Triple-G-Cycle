package com.maad.triple_gcycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.databinding.ActivitySignUpBinding

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

        binding.signUpBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passwordEt.text.toString()
            val confirmPass = binding.confirmPasswordEt.text.toString()
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
                            val user = User(userId, email, type)
                            db.collection("users").add(user).addOnSuccessListener {
                                when (type) {
                                    "Citizen", "Factory" -> {
                                        startActivity(Intent(this, HomeActivity::class.java))
                                        finish()
                                    }
                                }


                            }

                        } else
                            Toast.makeText(this, "Error Adding User", Toast.LENGTH_SHORT).show()
                    }
        }

    }

}