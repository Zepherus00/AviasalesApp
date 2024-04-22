package com.example.data.network

import com.example.data.model.TicketsResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitTicketsInstance {
    private const val BASE_URL = "https://drive.google.com/"

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val ticketsDataSource: TicketsDataSource = retrofit.create(TicketsDataSource::class.java)
}

interface TicketsDataSource {
    @GET("uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun fetchTickets(): TicketsResponse
}