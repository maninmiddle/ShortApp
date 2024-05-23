package com.baeyer.ashort.data.network.dto

import com.baeyer.ashort.domain.model.Arrival
import com.baeyer.ashort.domain.model.Departure
import com.baeyer.ashort.domain.model.HandLuggage
import com.baeyer.ashort.domain.model.Luggage
import com.squareup.moshi.Json

data class TicketDto(
   @field:Json(name = "arrival") val arrival: Arrival,
   @field:Json(name = "badge") val badge: String?,
   @field:Json(name = "company") val company: String,
   @field:Json(name = "departure") val departure: Departure,
   @field:Json(name = "hand_luggage") val hand_luggage: HandLuggage,
   @field:Json(name = "has_transfer") val has_transfer: Boolean,
   @field:Json(name = "has_visa_transfer") val has_visa_transfer: Boolean,
   @field:Json(name = "id") val id: Int,
   @field:Json(name = "is_exchangable") val is_exchangable: Boolean,
   @field:Json(name = "is_returnable") val is_returnable: Boolean,
   @field:Json(name = "luggage") val luggage: Luggage,
   @field:Json(name = "price") val price: OfferPriceDto,
   @field:Json(name = "provider_name") val provider_name: String
)
