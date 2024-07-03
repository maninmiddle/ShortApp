package com.maninmiddle.feature_tickets.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maninmiddle.feature_tickets.databinding.TicketItemBinding
import com.maninmiddle.feature_tickets.domain.model.TicketItem

class TicketsAdapter : ListAdapter<TicketItem, TicketsViewHolder>(
    TicketsDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val view = TicketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        val ticket = getItem(position)

        holder.bind(ticket, holder.binding.root.context)
    }
}