package com.example.storeapp.presentation.menu.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TicketsOfferPresentationModel
import com.example.domain.usecase.GetTicketsOffersUseCase
import com.example.domain.utilities.toPresentationModel
import com.example.storeapp.presentation.menu.tickets.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlightsViewModel(
    val getTicketsOffersUseCase: GetTicketsOffersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    private val _ticketsOffers = MutableLiveData<List<TicketsOfferPresentationModel>>()
    val ticketsOffers: LiveData<List<TicketsOfferPresentationModel>> = _ticketsOffers

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val domainTicketsOffers = getTicketsOffersUseCase.execute()
            val presentationTicketsOffers = domainTicketsOffers.map { it.toPresentationModel() }
            _ticketsOffers.postValue(presentationTicketsOffers)
            _state.value = State.Success
        }
    }
}