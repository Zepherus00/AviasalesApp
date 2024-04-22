package com.example.storeapp.presentation.di

import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetTicketsOffersUseCase
import com.example.domain.usecase.GetTicketsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetOffersUseCase(get()) }

    factory { GetTicketsOffersUseCase(get()) }

    factory { GetTicketsUseCase(get()) }
}