package com.maad.triple_gcycle.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class Request(
    val userId: String = "",
    val userType: String = "",
    val image: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val details: String = "",
    val destination: String = "",
    val requestId: String = "",
    var pointStatus: String = "Pending"
) : Parcelable