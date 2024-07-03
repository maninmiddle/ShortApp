package com.maninmiddle.feature_tickets.presentation.tickets

import com.maninmiddle.feature_tickets.domain.model.TicketItem

data class TicketsUiState(
    val tickets: List<TicketItem>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)