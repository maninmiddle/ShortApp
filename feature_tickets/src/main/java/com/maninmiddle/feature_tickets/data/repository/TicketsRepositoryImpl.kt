package com.maninmiddle.feature_tickets.data.repository

import com.maninmiddle.core.network.ApiService
import com.maninmiddle.core.util.ApiState
import com.maninmiddle.feature_tickets.data.mapper.toListTicketItem
import com.maninmiddle.feature_tickets.domain.model.TicketItem
import com.maninmiddle.feature_tickets.domain.repository.TicketsRepository

class TicketsRepositoryImpl(
    private val apiService: ApiService
) : TicketsRepository {
    override suspend fun getTickets(): ApiState<List<TicketItem>> {
        return try {
            ApiState.Success(data = apiService.getTickets().toListTicketItem())
        } catch (e: Exception) {
            e.printStackTrace()
            ApiState.Error(message = e.message ?: "An unknown error occurred")
        }
    }
}