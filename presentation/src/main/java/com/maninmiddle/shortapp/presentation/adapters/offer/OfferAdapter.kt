package com.maninmiddle.shortapp.presentation.adapters.offer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.baeyer.ashort.domain.model.OfferItem
import com.maninmiddle.presentation.databinding.OfferItemBinding

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