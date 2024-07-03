package com.maninmiddle.feature_flights.presentation.flights

import com.maninmiddle.feature_flights.domain.model.OfferItem

data class FlightsUiState(
    val offers: List<OfferItem>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)