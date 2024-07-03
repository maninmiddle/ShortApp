package com.maninmiddle.feature_flights.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.maninmiddle.feature_flights.domain.model.OfferItem

class OfferDiffCallback : DiffUtil.ItemCallback<OfferItem>() {
    override fun areItemsTheSame(oldItem: OfferItem, newItem: OfferItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OfferItem, newItem: OfferItem): Boolean {
        return oldItem == newItem
    }
}