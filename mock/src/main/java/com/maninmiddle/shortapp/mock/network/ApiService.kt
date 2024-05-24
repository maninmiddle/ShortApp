package com.maninmiddle.shortapp.data.network

import com.baeyer.ashort.data.network.dto.OfferDto
import com.baeyer.ashort.data.network.dto.TicketDto
import com.baeyer.ashort.data.network.dto.TicketOfferDto
import retrofit2.http.GET

interface ApiService {
    @GET("offers")
    suspend fun getOffers(): List<OfferDto>

    @GET("tickets_offers")
    suspend fun getTicketOffers(): List<TicketOfferDto>

    @GET("tickets")
    suspend fun getTickets(): List<TicketDto>
}