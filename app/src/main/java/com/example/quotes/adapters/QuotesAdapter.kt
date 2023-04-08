package com.example.quotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.R
import com.example.quotes.models.QuotesModel

class QuotesAdapter(private val dataList: List<QuotesModel>) :
    RecyclerView.Adapter<QuotesAdapter.ViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewModel(view)
    }

    override fun onBindViewHolder(holder: QuotesAdapter.ViewModel, position: Int) {
        val data = dataList[position]
        holder.quote.text = data.quote
        holder.author.text = data.author
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewModel(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val quote: TextView = itemView.findViewById(R.id.txt_quote)
        val author: TextView = itemView.findViewById(R.id.txt_author)
    }
}