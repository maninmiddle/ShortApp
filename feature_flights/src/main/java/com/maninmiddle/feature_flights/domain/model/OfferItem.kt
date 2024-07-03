package com.maninmiddle.feature_flights.domain.model

data class OfferItem(
    val id: Int,
    val title: String,
    val town: String,
    val price: OfferPrice
)