package com.example.domain.model

data class TicketsOfferPresentationModel(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Int
)
