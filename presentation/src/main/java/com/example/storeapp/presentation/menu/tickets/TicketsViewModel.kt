package com.example.storeapp.presentation.menu.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.OfferPresentationModel
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.utilities.toPresentationModel
import com.example.storeapp.presentation.menu.tickets.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TicketsViewModel(
    val getOffersUseCase: GetOffersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    private val _offers = MutableLiveData<List<OfferPresentationModel>>()
    val offers: LiveData<List<OfferPresentationModel>> = _offers

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val domainOffers = getOffersUseCase.execute()
            val presentationOffers = domainOffers.map { it.toPresentationModel() }
            _offers.postValue(presentationOffers)
            _state.value = State.Success
        }
    }
}