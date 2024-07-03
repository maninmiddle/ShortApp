package com.maninmiddle.feature_flights.data.mapper

import com.maninmiddle.core.network.dto.OfferDto
import com.maninmiddle.core.network.dto.OfferPriceDto
import com.maninmiddle.feature_flights.domain.model.OfferItem
import com.maninmiddle.feature_flights.domain.model.OfferPrice


fun OfferPriceDto.toOfferPrice(): OfferPrice {
    return OfferPrice(
        value = value
    )
}

fun List<OfferDto>.toListOfferItem(): List<OfferItem> {
    return this.map { offerDto ->
        OfferItem(
            id = offerDto.id,
            price = offerDto.price.toOfferPrice(),
            title = offerDto.title,
            town = offerDto.town
        )
    }
}