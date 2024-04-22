package com.example.data.utilities

import com.example.data.model.OfferDataModel
import com.example.data.model.TicketsDataModel
import com.example.data.model.TicketsOfferDataModel
import com.example.domain.model.ArrivalDomain
import com.example.domain.model.DepartureDomain
import com.example.domain.model.HandLuggageDomain
import com.example.domain.model.LuggageDomain
import com.example.domain.model.LuggageDomainPrice
import com.example.domain.model.OfferDomainModel
import com.example.domain.model.PriceOfferDomainModel
import com.example.domain.model.PriceTicketsDomain
import com.example.domain.model.PriceTicketsOfferDomainModel
import com.example.domain.model.TicketsDomainModel
import com.example.domain.model.TicketsOfferDomainModel

fun OfferDataModel.toDomainModel(): OfferDomainModel {
    return OfferDomainModel(
        id = this.id,
        title = this.title,
        town = this.town,
        price = PriceOfferDomainModel(value = this.price.value)
    )
}

fun TicketsOfferDataModel.toDomainModel(): TicketsOfferDomainModel {
    return TicketsOfferDomainModel(
        id = this.id,
        title = this.title,
        timeRange = this.timeRange,
        price = PriceTicketsOfferDomainModel(value = this.price.value)
    )
}

fun TicketsDataModel.toDomainModel(): TicketsDomainModel {
    return TicketsDomainModel(
        id = this.id,
        price = PriceTicketsDomain(value = this.price.value),
        badge = this.badge,
        providerName = this.providerName,
        company = this.company,
        departure = DepartureDomain(
            town = this.departure.town,
            date = this.departure.date,
            airport = this.departure.airport
        ),
        arrival = ArrivalDomain(
            town = this.arrival.town,
            date = this.arrival.date,
            airport = this.arrival.airport
        ),
        hasTransfer = this.hasTransfer,
        hasVisaTransfer = this.hasVisaTransfer,
        luggage = LuggageDomain(
            hasLuggage = this.luggage.hasLuggage,
            price = LuggageDomainPrice(this.luggage.price?.value ?: 0)
        ),
        handLuggage = HandLuggageDomain(
            hasHandLuggage = this.handLuggage.hasHandLuggage,
            size = this.handLuggage.size
        ),
        isReturnable = this.isReturnable,
        isExchangable = this.isExchangable
    )
}