package com.maninmiddle.shortapp.di

import com.maninmiddle.core.util.FragmentProvider
import com.maninmiddle.shortapp.util.fragmentProvider.FragmentProviderImpl
import org.koin.dsl.module

val appModule = module {
    single<FragmentProvider> { FragmentProviderImpl() }
}