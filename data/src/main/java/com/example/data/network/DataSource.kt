package com.example.data.network

import com.example.data.model.OfferResponse
import com.example.data.model.TicketsOfferResponse
import com.example.data.model.TicketsResponse
import java.net.ConnectException

class DataSource {
    suspend fun loadOfferData(): OfferResponse {
        val data: OfferResponse
        try {
            data = RetrofitOfferInstance.offerDataSource.fetchOffers()
        } catch (t: Throwable) {
            throw ConnectException(t.message)
        }
        return data
    }

    suspend fun loadTicketsOfferData(): TicketsOfferResponse {
        val data: TicketsOfferResponse
        try {
            data = RetrofitTicketsOfferInstance.ticketsOfferDataSource.fetchTicketsOffers()
        } catch (t: Throwable) {
            throw ConnectException(t.message)
        }
        return data
    }

    suspend fun loadTicketsData(): TicketsResponse {
        val data: TicketsResponse
        try {
            data = RetrofitTicketsInstance.ticketsDataSource.fetchTickets()
        } catch (t: Throwable) {
            throw ConnectException(t.message)
        }
        return data
    }
}