package com.maninmiddle.feature_tickets_offer.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.maninmiddle.feature_tickets_offer.domain.model.TicketOfferItem

class TicketsOfferDiffCallback : DiffUtil.ItemCallback<TicketOfferItem>() {
    override fun areItemsTheSame(oldItem: TicketOfferItem, newItem: TicketOfferItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TicketOfferItem, newItem: TicketOfferItem): Boolean {
        return oldItem == newItem
    }
}