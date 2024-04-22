package com.example.domain.model

data class TicketsDomainModel(
    val id: Int,
    val badge: String?,
    val price: PriceTicketsDomain,
    val providerName: String,
    val company: String,
    val departure: DepartureDomain,
    val arrival: ArrivalDomain,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: LuggageDomain,
    val handLuggage: HandLuggageDomain,
    val isReturnable: Boolean,
    val isExchangable: Boolean
)

data class PriceTicketsDomain(
    val value: Int
)

data class DepartureDomain(
    val town: String,
    val date: String,
    val airport: String
)

data class ArrivalDomain(
    val town: String,
    val date: String,
    val airport: String
)

data class LuggageDomain(
    val hasLuggage: Boolean,
    val price: LuggageDomainPrice?
)

data class LuggageDomainPrice(
    val value: Int
)

data class HandLuggageDomain(
    val hasHandLuggage: Boolean,
    val size: String?
)