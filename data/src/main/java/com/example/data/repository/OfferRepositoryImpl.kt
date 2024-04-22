package com.example.data.repository

import com.example.data.network.DataSource
import com.example.data.utilities.toDomainModel
import com.example.domain.model.OfferDomainModel
import com.example.domain.repository.OfferRepository

class OfferRepositoryImpl : OfferRepository {
    override suspend fun getOffers(): List<OfferDomainModel> {
        val response = DataSource().loadOfferData().offers
        val domainResponse = response.map { it.toDomainModel() }
        return domainResponse
    }
}