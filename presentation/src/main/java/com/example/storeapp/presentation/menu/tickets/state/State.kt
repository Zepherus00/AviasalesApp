package com.example.storeapp.presentation.menu.tickets.state

sealed class State {
    data object Loading : State()
    data object Success : State()
}