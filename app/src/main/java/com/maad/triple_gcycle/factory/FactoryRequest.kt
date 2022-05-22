package com.maad.triple_gcycle.factory

import com.maad.triple_gcycle.request.Request

class FactoryRequest(
    userId: String,
    userType: String,
    image: String,
    lat: Double,
    lon: Double,
    details: String,
    destination: String,
    //requestId: String,
    //pointStatus: String
    val day: String = "",
    val time: String = ""
) : Request(userId, userType, image, lat, lon, details, destination/*, requestId*/)