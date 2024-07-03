package com.maninmiddle.feature_tickets_offer.presentation.tickets_offer

import com.maninmiddle.feature_tickets_offer.domain.model.TicketOfferItem

data class TicketsOfferUiState(
    val ticketsOffer: List<TicketOfferItem>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)