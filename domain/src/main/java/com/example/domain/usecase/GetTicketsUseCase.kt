package com.example.domain.usecase

import com.example.domain.model.TicketsDomainModel
import com.example.domain.repository.TicketsRepository

class GetTicketsUseCase(private val repository: TicketsRepository) {

    suspend fun execute(): List<TicketsDomainModel> {
        return repository.getTickets()
    }
}