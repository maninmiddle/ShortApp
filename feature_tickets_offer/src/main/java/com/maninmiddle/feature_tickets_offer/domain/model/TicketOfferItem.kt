package com.maninmiddle.feature_tickets_offer.domain.model

data class TicketOfferItem(
    val id: Int,
    val price: OfferPrice,
    val time_range: List<String>,
    val title: String
)