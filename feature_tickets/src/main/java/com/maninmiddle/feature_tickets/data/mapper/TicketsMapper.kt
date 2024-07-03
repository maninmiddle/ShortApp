package com.maninmiddle.feature_tickets.data.mapper

import com.maninmiddle.core.network.dto.ArrivalDto
import com.maninmiddle.core.network.dto.DepartureDto
import com.maninmiddle.core.network.dto.HandLuggageDto
import com.maninmiddle.core.network.dto.LuggageDto
import com.maninmiddle.core.network.dto.PriceXDto
import com.maninmiddle.core.network.dto.TicketDto
import com.maninmiddle.feature_tickets.domain.model.Arrival
import com.maninmiddle.feature_tickets.domain.model.Departure
import com.maninmiddle.feature_tickets.domain.model.HandLuggage
import com.maninmiddle.feature_tickets.domain.model.Luggage
import com.maninmiddle.feature_tickets.domain.model.PriceX
import com.maninmiddle.feature_tickets.domain.model.TicketItem

fun ArrivalDto?.toArrival(): Arrival {
    return Arrival(
        airport = this?.airport ?: "",
        date = this?.date ?: "",
        town = this?.town ?: ""
    )
}

fun DepartureDto?.toDeparture(): Departure {
    return Departure(
        airport = this?.airport ?: "",
        date = this?.date ?: "",
        town = this?.town ?: ""
    )
}

fun HandLuggageDto?.toHandLuggage(): HandLuggage {
    return HandLuggage(
        has_hand_luggage = this?.has_hand_luggage ?: false,
        size = this?.size ?: ""
    )
}

fun LuggageDto?.toLuggage(): Luggage {
    return Luggage(
        has_luggage = this?.has_luggage ?: false,
        price = this?.price?.toPriceX() ?: PriceX(0)
    )
}

fun PriceXDto?.toPriceX(): PriceX {
    return PriceX(
        value = this?.value ?: 0
    )
}

fun List<TicketDto>.toListTicketItem(): List<TicketItem> {
    return this.map { ticketDto ->
        TicketItem(
            arrival = ticketDto.arrival.toArrival(),
            badge = ticketDto.badge,
            company = ticketDto.company,
            departure = ticketDto.departure.toDeparture(),
            hand_luggage = ticketDto.hand_luggage.toHandLuggage(),
            has_transfer = ticketDto.has_transfer,
            has_visa_transfer = ticketDto.has_visa_transfer,
            id = ticketDto.id,
            is_exchangable = ticketDto.is_exchangable,
            is_returnable = ticketDto.is_returnable,
            luggage = ticketDto.luggage.toLuggage(),
            price = ticketDto.price.toPriceX(),
            provider_name = ticketDto.provider_name
        )
    }
}