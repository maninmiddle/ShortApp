package com.baeyer.ashort.data.network.dto

import com.squareup.moshi.Json

data class OfferPriceDto (
   @field:Json(name = "value") val value: Int
)