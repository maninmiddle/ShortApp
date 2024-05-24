package com.maninmiddle.shortapp.presentation.fragments.ticketsOffer

import com.baeyer.ashort.domain.model.TicketOfferItem

data class TicketsOfferUiState(
    val ticketsOffer: List<TicketOfferItem>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)