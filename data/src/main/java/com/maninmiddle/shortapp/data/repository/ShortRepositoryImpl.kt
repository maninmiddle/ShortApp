package com.maninmiddle.shortapp.data.repository

import com.baeyer.ashort.domain.model.OfferItem
import com.baeyer.ashort.domain.model.TicketItem
import com.baeyer.ashort.domain.model.TicketOfferItem
import com.maninmiddle.shortapp.data.mapper.toOfferItemList
import com.maninmiddle.shortapp.data.mapper.toTicketItemList
import com.maninmiddle.shortapp.data.mapper.toTicketOfferItemList
import com.maninmiddle.shortapp.data.network.ApiService
import com.maninmiddle.shortapp.domain.repository.ShortRepository
import com.maninmiddle.shortapp.domain.util.ApiState
import javax.inject.Inject

class ShortRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): ShortRepository {
    override suspend fun getOffers(): ApiState<List<OfferItem>> {
        return try {
            ApiState.Success(
                data = apiService.getOffers().toOfferItemList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ApiState.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getTicketsOffer(): ApiState<List<TicketOfferItem>> {
        return try {
            ApiState.Success(
                data = apiService.getTicketOffers().toTicketOfferItemList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ApiState.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getTickets(): ApiState<List<TicketItem>> {
        return try {
            ApiState.Success(
                data = apiService.getTickets().toTicketItemList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ApiState.Error(e.message ?: "An unknown error occurred")
        }
    }
}