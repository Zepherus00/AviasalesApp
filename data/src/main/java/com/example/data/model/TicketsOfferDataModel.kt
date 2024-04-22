package com.example.data.model

import com.google.gson.annotations.SerializedName

data class TicketsOfferDataModel(
    val id: Int,
    val title: String,
    @SerializedName("time_range")
    val timeRange: List<String>,
    val price: PriceTicketsOfferDataModel
)

data class PriceTicketsOfferDataModel(
    val value: Int
)

data class TicketsOfferResponse(
    @SerializedName("tickets_offers")
    val ticketsOffers: List<TicketsOfferDataModel>
)
