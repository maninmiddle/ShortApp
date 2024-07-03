package com.maninmiddle.feature_flights.data.repository

import com.maninmiddle.core.network.ApiService
import com.maninmiddle.core.util.ApiState
import com.maninmiddle.feature_flights.data.mapper.toListOfferItem
import com.maninmiddle.feature_flights.domain.model.OfferItem
import com.maninmiddle.feature_flights.domain.repository.FlightsRepository

class FlightsRepositoryImpl(
    private val apiService: ApiService
) : FlightsRepository {
    override suspend fun getOffers(): ApiState<List<OfferItem>> {
        return try {
            ApiState.Success(data = apiService.getOffers().toListOfferItem())
        } catch (e: Exception) {
            e.printStackTrace()
            ApiState.Error(message = e.message ?: "An unknown error occurred")
        }
    }
}