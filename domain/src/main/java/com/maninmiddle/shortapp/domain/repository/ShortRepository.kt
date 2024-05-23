package com.maninmiddle.shortapp.domain.repository

import com.baeyer.ashort.domain.model.OfferItem
import com.baeyer.ashort.domain.model.TicketItem
import com.baeyer.ashort.domain.model.TicketOfferItem
import com.maninmiddle.shortapp.domain.util.ApiState

interface ShortRepository {
    suspend fun getOffers(): ApiState<List<OfferItem>>
    suspend fun getTicketsOffer(): ApiState<List<TicketOfferItem>>
    suspend fun getTickets(): ApiState<List<TicketItem>>
}