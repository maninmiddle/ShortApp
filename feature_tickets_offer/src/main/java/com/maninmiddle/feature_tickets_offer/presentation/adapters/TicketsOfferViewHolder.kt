package com.maninmiddle.feature_tickets_offer.presentation.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.maninmiddle.feature_tickets_offer.R
import com.maninmiddle.feature_tickets_offer.databinding.TicketOfferItemBinding
import com.maninmiddle.feature_tickets_offer.domain.model.TicketOfferItem

class TicketsOfferViewHolder(val binding: TicketOfferItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(ticketOfferItem: TicketOfferItem, context: Context) {
        binding.tvPrice.text =
            context.getString(R.string.stringPriceFormat, ticketOfferItem.price.value)
        binding.tvName.text = ticketOfferItem.title
        binding.tvTimeRange.text = ticketOfferItem.time_range.toString()

        when (ticketOfferItem.title) {
            "Уральские авиалинии" -> binding.circle.setCardBackgroundColor(context.getColor(R.color.colorRed))
            "Победа" -> binding.circle.setCardBackgroundColor(context.getColor(R.color.colorBlue))
            "NordStar" -> binding.circle.setCardBackgroundColor(context.getColor(R.color.white))
        }
    }
}