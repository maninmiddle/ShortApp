package com.baeyer.ashort.domain.model

data class OfferItem(
    val id: Int,
    val price: OfferPrice,
    val title: String,
    val town: String
)