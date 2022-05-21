package com.maad.triple_gcycle.citizen

import com.maad.triple_gcycle.factory.User

class Citizen(
    val points: Int = 0,
    id: String = "",
    email: String = "",
    userType: String = "",
    profile: String = "",
) : User(id, email, userType, profile)