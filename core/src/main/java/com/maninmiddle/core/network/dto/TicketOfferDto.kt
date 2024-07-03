package com.maninmiddle.core.network.dto

import com.squareup.moshi.Json

data class TicketOfferDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "price") val price: OfferPriceDto,
    @field:Json(name = "time_range") val time_range: List<String>,
    @field:Json(name = "title") val title: String
)