package com.maad.triple_gcycle.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.triple_gcycle.appprovider.AppProviderHomeActivity
import com.maad.triple_gcycle.bank.BankHomeActivity
import com.maad.triple_gcycle.databinding.ActivitySignInBinding
import com.maad.triple_gcycle.ministry.MinistryHomeActivity
import com.maad.triple_gcycle.citizen.CitizenHomeActivity
import com.maad.triple_gcycle.factory.FactoryHomeActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        binding.signUpTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        binding.signInBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            //val password = binding.passwordEt.text.toString()
            //val email = "c1@gmail.com"
            val password = "123456"
            if (email.isEmpty() || password.isEmpty())
                Toast.makeText(this, "You forgot to fill the fields", Toast.LENGTH_SHORT).show()
            else {
                val auth = Firebase.auth
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful)
                            getUserInfo(task.result.user!!.uid)
                        else {
                            Toast.makeText(
                                this, "Oops, ${task.exception?.localizedMessage}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

    }

    private fun getUserInfo(id: String) {
        db
            .collection("users")
            .document(id)
            .get()
            .addOnSuccessListener {
                val userType = it.getString("userType")!!

                val editor = getSharedPreferences("data", MODE_PRIVATE).edit()
                editor.putString("id", id)
                editor.putString("type", userType)
                editor.apply()

                when (userType) {
                    "Citizen" -> {
                        startActivity(Intent(this, CitizenHomeActivity::class.java))
                        finishAffinity()
                    }
                    "Factory" -> {
                        startActivity(Intent(this, FactoryHomeActivity::class.java))
                        finishAffinity()
                    }
                    "AppProvider" -> {
                        startActivity(Intent(this, AppProviderHomeActivity::class.java))
                        finishAffinity()
                    }
                    "Health", "Environment" -> {
                        val i = Intent(this, MinistryHomeActivity::class.java)
                        i.putExtra("ministry", userType)
                        startActivity(i)
                        finishAffinity()
                    }
                    "Bank" -> {
                        startActivity(Intent(this, BankHomeActivity::class.java))
                        finishAffinity()
                    }
                }
            }

    }
}