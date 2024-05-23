package com.maninmiddle.shortapp.di

import com.maninmiddle.shortapp.data.network.ApiService
import com.maninmiddle.shortapp.data.repository.ShortRepositoryImpl
import com.maninmiddle.shortapp.domain.repository.ShortRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShortModule {

    @Provides
    fun provideShortRepository(apiService: ApiService): ShortRepository {
        return ShortRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(): ApiService =
        Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/maninmiddle/tickets/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}