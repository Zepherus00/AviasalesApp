package com.example.domain.repository

import com.example.domain.model.TicketsDomainModel

interface TicketsRepository {
    suspend fun getTickets(): List<TicketsDomainModel>
}