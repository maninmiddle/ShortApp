package com.maninmiddle.feature_tickets_offer.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maninmiddle.feature_tickets_offer.databinding.TicketOfferItemBinding
import com.maninmiddle.feature_tickets_offer.domain.model.TicketOfferItem

class TicketsOfferAdapter : ListAdapter<TicketOfferItem, TicketsOfferViewHolder>(
    TicketsOfferDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsOfferViewHolder {
        val view =
            TicketOfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketsOfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketsOfferViewHolder, position: Int) {
        val ticketOffer = getItem(position)

        holder.bind(ticketOffer, holder.binding.root.context)
    }
}