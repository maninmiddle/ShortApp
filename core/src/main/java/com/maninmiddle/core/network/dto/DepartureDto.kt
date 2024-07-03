package com.maninmiddle.core.network.dto

import com.squareup.moshi.Json

data class DepartureDto(
    @field:Json(name = "airport") val airport: String,
    @field:Json(name = "date") val date: String,
    @field:Json(name = "town") val town: String
)