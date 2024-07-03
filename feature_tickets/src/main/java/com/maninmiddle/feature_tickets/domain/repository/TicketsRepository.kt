package com.maninmiddle.feature_tickets.domain.repository

import com.maninmiddle.core.util.ApiState
import com.maninmiddle.feature_tickets.domain.model.TicketItem

interface TicketsRepository {
    suspend fun getTickets(): ApiState<List<TicketItem>>
}