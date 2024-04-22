package com.example.domain.usecase

import com.example.domain.model.TicketsOfferDomainModel
import com.example.domain.repository.TicketsOfferRepository

class GetTicketsOffersUseCase(private val repository: TicketsOfferRepository) {

    suspend fun execute(): List<TicketsOfferDomainModel> {
        return repository.getTicketsOffers()
    }
}