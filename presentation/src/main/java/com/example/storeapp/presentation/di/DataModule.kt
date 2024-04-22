package com.example.storeapp.presentation.di

import com.example.data.repository.OfferRepositoryImpl
import com.example.data.repository.TicketsOfferRepositoryImpl
import com.example.data.repository.TicketsRepositoryImpl
import com.example.domain.repository.OfferRepository
import com.example.domain.repository.TicketsOfferRepository
import com.example.domain.repository.TicketsRepository
import org.koin.dsl.module

val dataModule = module {

    single<OfferRepository> {
        OfferRepositoryImpl()
    }

    single<TicketsOfferRepository> {
        TicketsOfferRepositoryImpl()
    }

    single<TicketsRepository> {
        TicketsRepositoryImpl()
    }
}