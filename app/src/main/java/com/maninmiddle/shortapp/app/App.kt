package com.maninmiddle.shortapp.app

import android.app.Application
import com.maninmiddle.core.di.networkModule
import com.maninmiddle.feature_flights.di.flightsModule
import com.maninmiddle.feature_tickets.di.ticketsModule
import com.maninmiddle.feature_tickets_offer.di.ticketsOfferModule
import com.maninmiddle.shortapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                appModule,
                networkModule,
                ticketsModule,
                ticketsOfferModule,
                flightsModule
            )
        }
    }
}