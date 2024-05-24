package com.maninmiddle.shortapp.presentation.adapters.offer

import androidx.recyclerview.widget.DiffUtil
import com.baeyer.ashort.domain.model.OfferItem

class OfferDiffCallback: DiffUtil.ItemCallback<OfferItem>() {
    override fun areItemsTheSame(oldItem: OfferItem, newItem: OfferItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OfferItem, newItem: OfferItem): Boolean {
        return oldItem == newItem
    }
}