package com.maninmiddle.shortapp.presentation.fragments.tickets

import com.baeyer.ashort.domain.model.TicketItem

data class TicketsUiState(
    val tickets: List<TicketItem>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)