package com.maninmiddle.feature_tickets_offer.domain.repository

import com.maninmiddle.core.util.ApiState
import com.maninmiddle.feature_tickets_offer.domain.model.TicketOfferItem

interface TicketsOfferRepository {
    suspend fun getTicketsOffer(): ApiState<List<TicketOfferItem>>
}