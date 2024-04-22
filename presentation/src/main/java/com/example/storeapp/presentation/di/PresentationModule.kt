package com.example.storeapp.presentation.di

import com.example.storeapp.presentation.menu.tickets.AllTicketsViewModel
import com.example.storeapp.presentation.menu.tickets.FlightsViewModel
import com.example.storeapp.presentation.menu.tickets.TicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<TicketsViewModel> {
        TicketsViewModel(
            getOffersUseCase = get()
        )
    }

    viewModel<FlightsViewModel> {
        FlightsViewModel(
            getTicketsOffersUseCase = get()
        )
    }

    viewModel<AllTicketsViewModel> {
        AllTicketsViewModel(
            getTicketsUseCase = get()
        )
    }
}