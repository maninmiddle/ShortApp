package com.maninmiddle.shortapp.presentation.adapters.offer

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.baeyer.ashort.domain.model.OfferItem
import com.maninmiddle.presentation.R
import com.maninmiddle.presentation.databinding.OfferItemBinding

class OfferViewHolder(val binding: OfferItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(offer: OfferItem, context: Context) {
        binding.root.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_right))
        binding.tvTitle.text = offer.title
        binding.tvTown.text = offer.town
        binding.tvPrice.text = context.getString(R.string.stringMinPrice, offer.price.value)
        when (offer.id) {
            1 -> binding.ivOffer.setImageResource(R.drawable.offer1)
            2 -> binding.ivOffer.setImageResource(R.drawable.offer2)
            3 -> binding.ivOffer.setImageResource(R.drawable.offer3)
        }
    }
}