package com.example.domain.model

data class TicketsOfferDomainModel(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: PriceTicketsOfferDomainModel
)

data class PriceTicketsOfferDomainModel(
    val value: Int
)
