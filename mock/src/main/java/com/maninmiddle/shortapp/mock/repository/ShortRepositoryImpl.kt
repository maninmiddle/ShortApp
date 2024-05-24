package com.maninmiddle.shortapp.mock.repository

import com.baeyer.ashort.domain.model.Arrival
import com.baeyer.ashort.domain.model.Departure
import com.baeyer.ashort.domain.model.HandLuggage
import com.baeyer.ashort.domain.model.Luggage
import com.baeyer.ashort.domain.model.OfferItem
import com.baeyer.ashort.domain.model.OfferPrice
import com.baeyer.ashort.domain.model.PriceXX
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
) : ShortRepository {
    override suspend fun getOffers(): ApiState<List<OfferItem>> {
        return try {
            ApiState.Success(
                data = listOf(
                    OfferItem(
                        id = 0,
                        price = OfferPrice(0),
                        title = "test",
                        town = "new town"
                    )
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ApiState.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getTicketsOffer(): ApiState<List<TicketOfferItem>> {
        return try {
            ApiState.Success(
                data = listOf(
                    TicketOfferItem(
                        id = 0,
                        price = OfferPrice(0),
                        time_range = emptyList(),
                        title = "hello world"
                    )
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ApiState.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getTickets(): ApiState<List<TicketItem>> {
        return try {
            ApiState.Success(
                data = listOf(
                    TicketItem(
                        arrival = Arrival("Test", "", ""),
                        badge = null,
                        company = "maninmiddle company",
                        departure = Departure("Test 2", "", ""),
                        hand_luggage = HandLuggage(true, "3"),
                        has_transfer = true,
                        has_visa_transfer = true,
                        id = 0,
                        is_exchangable = false,
                        is_returnable = false,
                        luggage = Luggage(true, PriceXX(3)),
                        price = OfferPrice(3),
                        provider_name = "maninmiddle"
                    )
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ApiState.Error(e.message ?: "An unknown error occurred")
        }
    }
}