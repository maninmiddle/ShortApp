package com.maninmiddle.core.network.dto

import com.squareup.moshi.Json

data class HandLuggageDto(
    @field:Json(name = "has_hand_luggage") val has_hand_luggage: Boolean,
    @field:Json(name = "size") val size: String
)