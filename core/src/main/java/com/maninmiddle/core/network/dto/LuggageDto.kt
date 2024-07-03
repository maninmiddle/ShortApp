package com.maninmiddle.core.network.dto

import com.squareup.moshi.Json

data class LuggageDto(
    @field:Json(name = "has_luggage") val has_luggage: Boolean,
    @field:Json(name = "price") val price: PriceXDto
)