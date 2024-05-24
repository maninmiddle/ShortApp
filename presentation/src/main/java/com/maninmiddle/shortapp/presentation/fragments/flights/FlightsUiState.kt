package com.maninmiddle.shortapp.presentation.fragments.flights

import com.baeyer.ashort.domain.model.OfferItem

data class FlightsUiState(
    val offers: List<OfferItem>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)