package com.maninmiddle.feature_tickets.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.maninmiddle.feature_tickets.domain.model.TicketItem

class TicketsDiffCallback : DiffUtil.ItemCallback<TicketItem>() {
    override fun areItemsTheSame(oldItem: TicketItem, newItem: TicketItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TicketItem, newItem: TicketItem): Boolean {
        return oldItem == newItem
    }
}