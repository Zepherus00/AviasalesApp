package com.example.domain.model

data class OfferDomainModel(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceOfferDomainModel
)

data class PriceOfferDomainModel(
    val value: Int
)