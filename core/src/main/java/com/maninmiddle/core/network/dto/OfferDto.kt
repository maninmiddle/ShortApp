package com.maninmiddle.core.network.dto

import com.squareup.moshi.Json

data class OfferDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "town") val town: String,
    @field:Json(name = "price") val price: OfferPriceDto
)