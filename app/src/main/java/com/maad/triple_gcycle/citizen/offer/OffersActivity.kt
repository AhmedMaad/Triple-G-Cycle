package com.maad.triple_gcycle.citizen.offer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.triple_gcycle.R
import com.maad.triple_gcycle.databinding.ActivityOffersBinding

class OffersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOffersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val offers = arrayListOf<Offer>()
        offers.add(Offer(R.drawable.shopping_1, "Protein Department", R.drawable.discount_20, "28 Points", R.drawable.carrefour))
        offers.add(Offer(R.drawable.pharmacy_1, "Health Care", R.drawable.discount_50, "50 Points", R.drawable.el_ezaby))
        offers.add(Offer(R.drawable.shopping_2, "Grocery Department", R.drawable.discount_10, "15 Points", R.drawable.carrefour))
        offers.add(Offer(R.drawable.pharmacy_1, "Skin Care", R.drawable.discount_70, "100 Points", R.drawable.el_ezaby))

        val adapter = OffersAdapter(this, offers)
        binding.offersRv.adapter = adapter

    }

}