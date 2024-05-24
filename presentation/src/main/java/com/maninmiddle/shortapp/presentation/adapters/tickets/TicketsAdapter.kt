package com.maninmiddle.shortapp.presentation.adapters.tickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.baeyer.ashort.domain.model.TicketItem
import com.maninmiddle.presentation.databinding.TicketItemBinding

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