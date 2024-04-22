package com.example.data.network

import com.example.data.model.TicketsOfferResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitTicketsOfferInstance {
    private const val BASE_URL = "https://drive.google.com/"

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val ticketsOfferDataSource: TicketsOfferDataSource =
        retrofit.create(TicketsOfferDataSource::class.java)
}

interface TicketsOfferDataSource {
    @GET("uc?export=download&id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta")
    suspend fun fetchTicketsOffers(): TicketsOfferResponse
}