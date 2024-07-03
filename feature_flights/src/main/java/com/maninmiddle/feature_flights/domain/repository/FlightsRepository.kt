package com.maninmiddle.feature_flights.domain.repository

import com.maninmiddle.core.util.ApiState
import com.maninmiddle.feature_flights.domain.model.OfferItem

interface FlightsRepository {
    suspend fun getOffers(): ApiState<List<OfferItem>>
}