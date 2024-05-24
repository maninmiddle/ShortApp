package com.maninmiddle.shortapp.presentation.adapters.ticket_offer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.baeyer.ashort.domain.model.TicketOfferItem
import com.maninmiddle.presentation.databinding.TicketOfferItemBinding

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