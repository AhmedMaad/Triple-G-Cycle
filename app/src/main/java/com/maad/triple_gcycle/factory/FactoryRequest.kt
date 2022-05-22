package com.maad.triple_gcycle.factory

import com.maad.triple_gcycle.request.Request

class FactoryRequest(
    userId: String = "",
    userType: String = "",
    image: String = "",
    lat: Double = 0.0,
    lon: Double = 0.0,
    details: String = "",
    destination: String = "",
    val day: String = "",
    val time: String = ""
) : Request(userId, userType, image, lat, lon, details, destination)