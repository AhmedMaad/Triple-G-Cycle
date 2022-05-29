package com.maad.triple_gcycle.citizen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import com.maad.triple_gcycle.R
import com.maad.triple_gcycle.citizen.about.AboutUsActivity
import com.maad.triple_gcycle.citizen.about.ContactUsActivity
import com.maad.triple_gcycle.citizen.about.InstructionsActivity
import com.maad.triple_gcycle.citizen.about.ProfileActivity
import com.maad.triple_gcycle.citizen.offer.OffersActivity
import com.maad.triple_gcycle.databinding.ActivityCitizenHomeBinding
import com.maad.triple_gcycle.request.RequestsActivity

class CitizenHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCitizenHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.requestCv.setOnClickListener {
            startActivity(Intent(this, RequestsActivity::class.java))
        }

        binding.pointsCv.setOnClickListener {
            startActivity(Intent(this, PointsActivity::class.java))
        }

        binding.offersCv.setOnClickListener {
            startActivity(Intent(this, OffersActivity::class.java))
        }

        binding.moreIv.setOnClickListener {
            val popupMenu = PopupMenu(this, binding.moreIv)
            popupMenu.menuInflater.inflate(R.menu.more_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.profile_item ->
                        startActivity(Intent(this, ProfileActivity::class.java))
                    R.id.instructions_item ->
                        startActivity(Intent(this, InstructionsActivity::class.java))
                    R.id.contact_us_item ->
                        startActivity(Intent(this, ContactUsActivity::class.java))
                    R.id.about_us_item ->
                        startActivity(Intent(this, AboutUsActivity::class.java))
                }

                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }

}