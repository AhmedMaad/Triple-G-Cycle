package com.maad.triple_gcycle.citizen.offer

import androidx.annotation.DrawableRes

class Offer(
    @DrawableRes val bg: Int,
    val name: String,
    @DrawableRes val discount: Int,
    val points: String,
    @DrawableRes val provider: Int
)