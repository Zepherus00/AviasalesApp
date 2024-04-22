package com.example.data.network

import com.example.data.model.OfferResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitOfferInstance {
    private const val BASE_URL = "https://drive.google.com/"

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val offerDataSource: OfferDataSource = retrofit.create(OfferDataSource::class.java)
}

interface OfferDataSource {
    @GET("uc?export=download&id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav")
    suspend fun fetchOffers(): OfferResponse
}