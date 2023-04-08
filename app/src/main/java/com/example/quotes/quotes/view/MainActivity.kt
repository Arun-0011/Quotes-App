package com.example.quotes.quotes.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.adapters.QuotesAdapter
import com.example.quotes.databinding.ActivityMainBinding
import com.example.quotes.quotes.viewmodels.QuotesViewModel
import com.example.quotes.util.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val viewModel: QuotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        quoteApi()
        observe()
    }

    private fun quoteApi() {
        viewModel.fetchQuotes()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.quotesResponse.collect {
                when (it) {
                    is ApiResponse.Loading -> {

                    }
                    is ApiResponse.Success -> {
                        binding?.recyclerView?.layoutManager =
                            LinearLayoutManager(this@MainActivity)
                        val adapter = it.data?.let { it1 -> QuotesAdapter(it1) }
                        binding?.recyclerView?.adapter = adapter
                    }
                    is ApiResponse.Error -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}