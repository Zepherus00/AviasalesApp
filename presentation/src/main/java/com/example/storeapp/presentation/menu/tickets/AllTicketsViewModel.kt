package com.example.storeapp.presentation.menu.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TicketsPresentationModel
import com.example.domain.usecase.GetTicketsUseCase
import com.example.domain.utilities.toPresentationModel
import com.example.storeapp.presentation.menu.tickets.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllTicketsViewModel(
    val getTicketsUseCase: GetTicketsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    private val _tickets = MutableLiveData<List<TicketsPresentationModel>>()
    val tickets: LiveData<List<TicketsPresentationModel>> = _tickets

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val domainTickets = getTicketsUseCase.execute()
            val presentationTickets = domainTickets.map { it.toPresentationModel() }
            _tickets.postValue(presentationTickets)
            _state.value = State.Success
        }
    }
}