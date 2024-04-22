package com.example.storeapp.presentation.menu.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TicketsOfferPresentationModel
import com.example.storeapp.APP_ACTIVITY
import com.example.storeapp.R
import com.example.storeapp.databinding.RvFlightBinding
import com.example.storeapp.presentation.utilities.toCurrencyFormat

class FlightsAdapter : RecyclerView.Adapter<FlightsAdapter.FlightsViewHolder>() {

    private var flights: List<TicketsOfferPresentationModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightsViewHolder {
        val binding =
            RvFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlightsViewHolder, position: Int) {
        val flight = flights[position]
        holder.bind(flight)
    }

    override fun getItemCount(): Int {
        return flights.size
    }

    fun submitList(newFlights: List<TicketsOfferPresentationModel>) {
        flights = newFlights
        notifyDataSetChanged()
    }


    class FlightsViewHolder(private val binding: RvFlightBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(flight: TicketsOfferPresentationModel) {
            binding.companyRvFlight.text = flight.title
            binding.timeRvFlight.text = flight.timeRange.joinToString(separator = " ")
            binding.priceRvFlight.text = APP_ACTIVITY.getString(
                R.string.price_with_currency,
                toCurrencyFormat(flight.price)
            )

            when (flight.id) {
                1 -> {
                    binding.imgStambulRvFlight.setImageResource(R.drawable.ic_circle_red)
                }

                10 -> {
                    binding.imgStambulRvFlight.setImageResource(R.drawable.ic_circle_blue)
                }

                else -> {
                    binding.imgStambulRvFlight.setImageResource(R.drawable.ic_circle_white)
                }
            }
        }
    }
}