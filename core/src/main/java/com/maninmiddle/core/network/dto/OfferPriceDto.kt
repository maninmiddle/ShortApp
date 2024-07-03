package com.maninmiddle.core.network.dto

import com.squareup.moshi.Json

data class OfferPriceDto (
    @field:Json(name = "value") val value: Int
)