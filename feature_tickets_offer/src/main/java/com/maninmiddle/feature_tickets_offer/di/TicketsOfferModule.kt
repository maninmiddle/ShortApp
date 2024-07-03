package com.maninmiddle.feature_tickets_offer.di

import com.maninmiddle.feature_tickets_offer.data.repository.TicketsOfferRepositoryImpl
import com.maninmiddle.feature_tickets_offer.domain.repository.TicketsOfferRepository
import com.maninmiddle.feature_tickets_offer.presentation.tickets_offer.TicketsOfferViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ticketsOfferModule = module {
    single<TicketsOfferRepository> { TicketsOfferRepositoryImpl(get()) }
    viewModel { TicketsOfferViewModel(get()) }
}