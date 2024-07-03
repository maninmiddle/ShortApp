package com.maninmiddle.core.network.dto

import com.squareup.moshi.Json

data class TicketDto(
    @field:Json(name = "arrival") val arrival: ArrivalDto,
    @field:Json(name = "badge") val badge: String?,
    @field:Json(name = "company") val company: String,
    @field:Json(name = "departure") val departure: DepartureDto,
    @field:Json(name = "hand_luggage") val hand_luggage: HandLuggageDto,
    @field:Json(name = "has_transfer") val has_transfer: Boolean,
    @field:Json(name = "has_visa_transfer") val has_visa_transfer: Boolean,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "is_exchangable") val is_exchangable: Boolean,
    @field:Json(name = "is_returnable") val is_returnable: Boolean,
    @field:Json(name = "luggage") val luggage: LuggageDto,
    @field:Json(name = "price") val price: PriceXDto,
    @field:Json(name = "provider_name") val provider_name: String
)