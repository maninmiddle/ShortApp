package com.maninmiddle.feature_flights.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maninmiddle.feature_flights.databinding.OfferItemBinding
import com.maninmiddle.feature_flights.domain.model.OfferItem

class OfferAdapter(val context: Context) : ListAdapter<OfferItem, OfferViewHolder>(
    OfferDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = OfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = getItem(position)

        holder.bind(offer, context)
    }
}