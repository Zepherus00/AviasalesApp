package com.example.storeapp.presentation.menu.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TicketsPresentationModel
import com.example.storeapp.APP_ACTIVITY
import com.example.storeapp.R
import com.example.storeapp.databinding.RvTicketBinding
import com.example.storeapp.presentation.utilities.calculateTimeDifference
import com.example.storeapp.presentation.utilities.extractTime
import com.example.storeapp.presentation.utilities.makeGone
import com.example.storeapp.presentation.utilities.toCurrencyFormat

class AllTicketsAdapter : RecyclerView.Adapter<AllTicketsAdapter.AllTicketsViewHolder>() {

    private var tickets: List<TicketsPresentationModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTicketsViewHolder {
        val binding =
            RvTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllTicketsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllTicketsViewHolder, position: Int) {
        val ticket = tickets[position]
        holder.bind(ticket)
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    fun submitList(newTickets: List<TicketsPresentationModel>) {
        tickets = newTickets
        notifyDataSetChanged()
    }


    class AllTicketsViewHolder(private val binding: RvTicketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ticket: TicketsPresentationModel) {
            with(binding) {
                if (ticket.badge == null)
                    specialInfoRvFlight.makeGone()
                else
                    specialInfoRvFlight.text = ticket.badge

                priceRvFlight.text = APP_ACTIVITY.getString(
                    R.string.price_with_currency,
                    toCurrencyFormat(ticket.price)
                )

                timeStartRvFlight.text = "${extractTime(ticket.departureDate)} - "

                airportStartRvFlight.text = ticket.departureAirport

                timeEndRvFlight.text = extractTime(ticket.arrivalDate)

                airportEndRvFlight.text = ticket.arrivalAirport

                moreInfoRvFlight.text =
                    calculateTimeDifference(ticket.arrivalDate, ticket.departureDate)

                if (!ticket.hasTransfer) transferRvFlight.text = "/Без пересадок"
            }
        }
    }
}