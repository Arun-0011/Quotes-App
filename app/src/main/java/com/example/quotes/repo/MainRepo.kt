package com.example.quotes.repo

import com.example.quotes.models.QuotesModel
import com.example.quotes.remote.ApiInterface
import com.example.quotes.util.ApiResponse
import javax.inject.Inject

class MainRepo @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun fetchQuotes(): ApiResponse<List<QuotesModel>?> {
        val response = apiInterface.quoteApi()
        return if(response.isSuccessful){
            ApiResponse.Success(response.body())
        }else{
            ApiResponse.Error(response.message())
        }
    }
}