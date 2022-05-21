package com.maad.triple_gcycle.citizen.offer

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maad.triple_gcycle.R

class OffersAdapter(val activity: Activity, val offers: ArrayList<Offer>)
    : RecyclerView.Adapter<OffersAdapter.OfferVH>(){

    class OfferVH(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.offer_name_tv)
        val bg: ImageView = view.findViewById(R.id.offer_bg_iv)
        val points:TextView = view.findViewById(R.id.offer_points_value)
        val provider: ImageView = view.findViewById(R.id.offer_provider_iv)
        val discount: ImageView = view.findViewById(R.id.offer_discount_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        OfferVH(activity.layoutInflater.inflate(R.layout.offers_list_item, parent, false))

    override fun onBindViewHolder(holder: OffersAdapter.OfferVH, position: Int) {
        holder.title.text = offers[position].name
        holder.bg.setImageResource(offers[position].bg)
        holder.points.text = offers[position].points
        holder.provider.setImageResource(offers[position].provider)
        holder.discount.setImageResource(offers[position].discount)
    }

    override fun getItemCount() = offers.size
}