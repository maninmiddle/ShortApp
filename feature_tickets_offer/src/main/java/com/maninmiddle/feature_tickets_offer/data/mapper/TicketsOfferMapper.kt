package com.maninmiddle.feature_tickets_offer.data.mapper

import com.maninmiddle.core.network.dto.OfferPriceDto
import com.maninmiddle.core.network.dto.TicketOfferDto
import com.maninmiddle.feature_tickets_offer.domain.model.OfferPrice
import com.maninmiddle.feature_tickets_offer.domain.model.TicketOfferItem

fun OfferPriceDto.toOfferPrice(): OfferPrice {
    return OfferPrice(
        value = value
    )
}

fun List<TicketOfferDto>.toTicketOfferItem(): List<TicketOfferItem> {
    return this.map { ticketOfferDto ->
        TicketOfferItem(
            id = ticketOfferDto.id,
            price = ticketOfferDto.price.toOfferPrice(),
            time_range = ticketOfferDto.time_range,
            title = ticketOfferDto.title
        )
    }
}