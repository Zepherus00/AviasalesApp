package com.example.data.repository

import com.example.data.network.DataSource
import com.example.data.utilities.toDomainModel
import com.example.domain.model.TicketsOfferDomainModel
import com.example.domain.repository.TicketsOfferRepository

class TicketsOfferRepositoryImpl : TicketsOfferRepository {
    override suspend fun getTicketsOffers(): List<TicketsOfferDomainModel> {
        val response = DataSource().loadTicketsOfferData().ticketsOffers
        val domainResponse = response.map { it.toDomainModel() }
        return domainResponse
    }
}