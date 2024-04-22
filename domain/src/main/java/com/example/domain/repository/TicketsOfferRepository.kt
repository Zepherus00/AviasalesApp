package com.example.domain.repository

import com.example.domain.model.OfferDomainModel
import com.example.domain.model.TicketsOfferDomainModel

interface TicketsOfferRepository {
    suspend fun getTicketsOffers(): List<TicketsOfferDomainModel>
}