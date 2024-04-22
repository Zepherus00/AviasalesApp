package com.example.domain.usecase

import com.example.domain.model.OfferDomainModel
import com.example.domain.repository.OfferRepository

class GetOffersUseCase(private val repository: OfferRepository) {

    suspend fun execute(): List<OfferDomainModel> {
        return repository.getOffers()
    }
}