package com.maad.triple_gcycle.ministry

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maad.triple_gcycle.R
import com.maad.triple_gcycle.factory.request.Request

class RequestAdapter(
    val activity: Activity,
    val requests: ArrayList<Request>,
    val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<RequestAdapter.RequestVH>() {

    interface ItemClickListener {
        fun onApproveButtonClick(position: Int)
        fun onRejectBtnClick(position: Int)
        fun onLocationClick(position: Int)
    }

    inner class RequestVH(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.request_iv)
        val location: TextView = view.findViewById(R.id.location_tv)
        val details: TextView = view.findViewById(R.id.details_tv)
        val approve: Button = view.findViewById(R.id.approve_btn)
        val reject: Button = view.findViewById(R.id.reject_btn)

        init {
            approve.setOnClickListener { itemClickListener.onApproveButtonClick(adapterPosition) }
            reject.setOnClickListener { itemClickListener.onRejectBtnClick(adapterPosition) }
            location.setOnClickListener { itemClickListener.onLocationClick(adapterPosition) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RequestVH(activity.layoutInflater.inflate(R.layout.request_list_item, parent, false))

    override fun onBindViewHolder(holder: RequestAdapter.RequestVH, position: Int) {
        Glide.with(activity).load(requests[position].image).into(holder.image)
        holder.details.text = requests[position].details
    }

    override fun getItemCount() = requests.size

}