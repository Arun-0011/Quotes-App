package com.example.quotes.remote

import com.example.quotes.models.QuotesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/quotes/50")
    suspend fun quoteApi(): Response<List<QuotesModel>>
}