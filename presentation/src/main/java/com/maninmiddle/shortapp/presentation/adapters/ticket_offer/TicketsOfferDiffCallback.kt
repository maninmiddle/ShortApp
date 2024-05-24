package com.maninmiddle.shortapp.presentation.adapters.ticket_offer

import androidx.recyclerview.widget.DiffUtil
import com.baeyer.ashort.domain.model.TicketOfferItem

class TicketsOfferDiffCallback: DiffUtil.ItemCallback<TicketOfferItem>() {
    override fun areItemsTheSame(oldItem: TicketOfferItem, newItem: TicketOfferItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TicketOfferItem, newItem: TicketOfferItem): Boolean {
        return oldItem == newItem
    }
}