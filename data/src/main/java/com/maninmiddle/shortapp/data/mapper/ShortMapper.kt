package com.maninmiddle.shortapp.data.mapper

import com.baeyer.ashort.data.network.dto.OfferDto
import com.baeyer.ashort.data.network.dto.OfferPriceDto
import com.baeyer.ashort.data.network.dto.TicketDto
import com.baeyer.ashort.data.network.dto.TicketOfferDto
import com.baeyer.ashort.domain.model.OfferItem
import com.baeyer.ashort.domain.model.OfferPrice
import com.baeyer.ashort.domain.model.TicketItem
import com.baeyer.ashort.domain.model.TicketOfferItem

fun OfferPriceDto.toOfferPrice(): OfferPrice {
    return OfferPrice(
        value = value
    )
}

fun List<TicketOfferDto>.toTicketOfferItemList(): List<TicketOfferItem> {
    return this.map { ticketOfferDto ->
        TicketOfferItem(
            id = ticketOfferDto.id,
            price = ticketOfferDto.price.toOfferPrice(),
            time_range = ticketOfferDto.time_range,
            title = ticketOfferDto.title
        )
    }
}

fun List<TicketDto>.toTicketItemList(): List<TicketItem> {
    return this.map { ticketDto ->
        TicketItem(
            arrival = ticketDto.arrival,
            badge = ticketDto.badge,
            company = ticketDto.company,
            departure = ticketDto.departure,
            hand_luggage = ticketDto.hand_luggage,
            has_transfer = ticketDto.has_transfer,
            has_visa_transfer = ticketDto.has_visa_transfer,
            id = ticketDto.id,
            is_exchangable = ticketDto.is_exchangable,
            is_returnable = ticketDto.is_returnable,
            luggage = ticketDto.luggage,
            price = ticketDto.price.toOfferPrice(),
            provider_name = ticketDto.provider_name
        )
    }
}

fun List<OfferDto>.toOfferItemList(): List<OfferItem> {
    return this.map { offerDto ->
        OfferItem(
            id = offerDto.id,
            price = offerDto.price.toOfferPrice(),
            title = offerDto.title,
            town = offerDto.town
        )
    }
}

fun OfferDto.toOfferItem(): OfferItem {
    return OfferItem(
        id = id,
        price = price.toOfferPrice(),
        title = title,
        town = town
    )
}

