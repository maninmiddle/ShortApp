package com.maninmiddle.feature_tickets.di

import com.maninmiddle.feature_tickets.data.repository.TicketsRepositoryImpl
import com.maninmiddle.feature_tickets.domain.repository.TicketsRepository
import com.maninmiddle.feature_tickets.presentation.tickets.TicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ticketsModule = module {
    single<TicketsRepository> { TicketsRepositoryImpl(get()) }
    viewModel { TicketsViewModel(get()) }
}