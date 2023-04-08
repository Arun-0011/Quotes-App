package com.example.quotes.quotes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotes.models.QuotesModel
import com.example.quotes.repo.MainRepo
import com.example.quotes.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(private val repo: MainRepo) : ViewModel() {
    private val _quotesResponse: MutableSharedFlow<ApiResponse<List<QuotesModel>?>> =
        MutableSharedFlow()
    val quotesResponse: SharedFlow<ApiResponse<List<QuotesModel>?>> = _quotesResponse

    fun fetchQuotes() {
        viewModelScope.launch {
            val response = repo.fetchQuotes()
            _quotesResponse.emit(response)
        }
    }
}