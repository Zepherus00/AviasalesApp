package com.example.data.model

import com.google.gson.annotations.SerializedName

data class TicketsDataModel(
    val id: Int,
    val badge: String?,
    val price: Price,
    @SerializedName("provider_name")
    val providerName: String,
    val company: String,
    val departure: Departure,
    val arrival: Arrival,
    @SerializedName("has_transfer")
    val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    @SerializedName("hand_luggage")
    val handLuggage: HandLuggage,
    @SerializedName("is_returnable")
    val isReturnable: Boolean,
    @SerializedName("is_exchangable")
    val isExchangable: Boolean
)

data class Price(
    val value: Int
)

data class Departure(
    val town: String,
    val date: String,
    val airport: String
)

data class Arrival(
    val town: String,
    val date: String,
    val airport: String
)

data class Luggage(
    @SerializedName("has_luggage")
    val hasLuggage: Boolean,
    val price: LuggagePrice?
)

data class LuggagePrice(
    val value: Int
)

data class HandLuggage(
    @SerializedName("has_hand_luggage")
    val hasHandLuggage: Boolean,
    val size: String?
)

data class TicketsResponse(
    val tickets: List<TicketsDataModel>
)