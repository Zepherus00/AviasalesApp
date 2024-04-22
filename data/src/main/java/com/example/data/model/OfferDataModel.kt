package com.example.data.model

data class OfferDataModel(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceOfferDataModel
)

data class PriceOfferDataModel(
    val value: Int
)

data class OfferResponse(
    val offers: List<OfferDataModel>
)