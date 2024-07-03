package com.maninmiddle.feature_tickets.presentation.adapters

import android.content.Context
import android.os.Build
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.maninmiddle.feature_tickets.R
import com.maninmiddle.feature_tickets.databinding.TicketItemBinding
import com.maninmiddle.feature_tickets.domain.model.TicketItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TicketsViewHolder(val binding: TicketItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ticket: TicketItem, context: Context) {
        if (ticket.badge != null) {
            binding.cvBadge.visibility = View.VISIBLE
            binding.tvBadge.text = ticket.badge
        }

        binding.departureAirport.text = ticket.departure.airport
        binding.arrivalAirport.text = ticket.arrival.airport
        binding.tvStartTime.text = dateToTimeFormatter(ticket.departure.date)
        binding.tvEndTime.text = dateToTimeFormatter(ticket.arrival.date)
        binding.tvPrice.text = context.getString(R.string.stringPriceFormat, ticket.price.value)

    }

    private fun dateToTimeFormatter(date: String): String {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return LocalDateTime.parse(date, formatter).toLocalTime().toString()
    }

}