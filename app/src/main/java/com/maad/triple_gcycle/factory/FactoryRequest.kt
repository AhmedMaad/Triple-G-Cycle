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
    var day: String = "",
    var time: String = ""
) : Request(userId, userType, image, lat, lon, details, destination)