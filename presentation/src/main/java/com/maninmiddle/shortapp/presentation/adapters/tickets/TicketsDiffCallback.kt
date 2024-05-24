package com.maninmiddle.shortapp.presentation.adapters.tickets

import androidx.recyclerview.widget.DiffUtil
import com.baeyer.ashort.domain.model.TicketItem

class TicketsDiffCallback : DiffUtil.ItemCallback<TicketItem>() {
    override fun areItemsTheSame(oldItem: TicketItem, newItem: TicketItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TicketItem, newItem: TicketItem): Boolean {
        return oldItem == newItem
    }
}