package com.maninmiddle.core.network

import com.maninmiddle.core.network.dto.OfferDto
import com.maninmiddle.core.network.dto.TicketDto
import com.maninmiddle.core.network.dto.TicketOfferDto
import retrofit2.http.GET

interface ApiService {
    @GET("offers")
    suspend fun getOffers(): List<OfferDto>

    @GET("tickets_offers")
    suspend fun getTicketOffers(): List<TicketOfferDto>

    @GET("tickets")
    suspend fun getTickets(): List<TicketDto>
}