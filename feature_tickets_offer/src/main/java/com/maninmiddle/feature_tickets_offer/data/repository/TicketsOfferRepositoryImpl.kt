package com.maninmiddle.feature_tickets_offer.data.repository

import com.maninmiddle.core.network.ApiService
import com.maninmiddle.core.util.ApiState
import com.maninmiddle.feature_tickets_offer.data.mapper.toTicketOfferItem
import com.maninmiddle.feature_tickets_offer.domain.model.TicketOfferItem
import com.maninmiddle.feature_tickets_offer.domain.repository.TicketsOfferRepository

class TicketsOfferRepositoryImpl(
    private val apiService: ApiService
) : TicketsOfferRepository {
    override suspend fun getTicketsOffer(): ApiState<List<TicketOfferItem>> {
        return try {
            ApiState.Success(data = apiService.getTicketOffers().toTicketOfferItem())
        } catch (e: Exception) {
            ApiState.Error(message = e.message ?: "an unknown error occurred")
        }
    }
}