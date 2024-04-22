package com.example.data.repository

import com.example.data.network.DataSource
import com.example.data.utilities.toDomainModel
import com.example.domain.model.TicketsDomainModel
import com.example.domain.repository.TicketsRepository

class TicketsRepositoryImpl : TicketsRepository {
    override suspend fun getTickets(): List<TicketsDomainModel> {
        val response = DataSource().loadTicketsData().tickets
        val domainResponse = response.map { it.toDomainModel() }
        return domainResponse
    }

}