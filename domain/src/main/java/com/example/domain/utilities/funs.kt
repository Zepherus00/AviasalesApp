package com.example.domain.utilities

import com.example.domain.model.OfferDomainModel
import com.example.domain.model.OfferPresentationModel
import com.example.domain.model.TicketsDomainModel
import com.example.domain.model.TicketsOfferDomainModel
import com.example.domain.model.TicketsOfferPresentationModel
import com.example.domain.model.TicketsPresentationModel

fun OfferDomainModel.toPresentationModel(): OfferPresentationModel {
    return OfferPresentationModel(
        id = this.id,
        title = this.title,
        town = this.town,
        price = this.price.value
    )
}

fun TicketsOfferDomainModel.toPresentationModel(): TicketsOfferPresentationModel {
    return TicketsOfferPresentationModel(
        id = this.id,
        title = this.title,
        timeRange = this.timeRange,
        price = this.price.value
    )
}

fun TicketsDomainModel.toPresentationModel(): TicketsPresentationModel {
    return TicketsPresentationModel(
        id = this.id,
        badge = this.badge,
        price = this.price.value,
        providerName = this.providerName,
        company = this.company,
        departureTown = this.departure.town,
        departureDate = this.departure.date,
        departureAirport = this.departure.airport,
        arrivalTown = this.arrival.town,
        arrivalDate = this.arrival.date,
        arrivalAirport = this.arrival.airport,
        hasTransfer = this.hasTransfer,
        hasVisaTransfer = this.hasVisaTransfer,
        hasLuggage = this.luggage.hasLuggage,
        luggagePrice = this.luggage.price?.value,
        hasHandLuggage = this.handLuggage.hasHandLuggage,
        handLuggageSize = this.handLuggage.size,
        isReturnable = this.isReturnable,
        isExchangable = this.isExchangable
    )
}