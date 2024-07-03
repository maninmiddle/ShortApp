package com.maninmiddle.feature_flights.di

import com.maninmiddle.feature_flights.data.repository.FlightsRepositoryImpl
import com.maninmiddle.feature_flights.domain.repository.FlightsRepository
import com.maninmiddle.feature_flights.presentation.flights.FlightsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val flightsModule = module {
    single<FlightsRepository> { FlightsRepositoryImpl(get()) }
    viewModel { FlightsViewModel(get()) }
}