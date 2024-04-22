package com.example.storeapp.presentation.menu.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.domain.model.OfferPresentationModel
import com.example.storeapp.APP_ACTIVITY
import com.example.storeapp.R
import com.example.storeapp.databinding.RvPosterItemBinding
import com.example.storeapp.presentation.utilities.getDrawableFromId
import com.example.storeapp.presentation.utilities.multiRoundedCorner16
import com.example.storeapp.presentation.utilities.toCurrencyFormat

class OfferAdapter : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    private var offers: List<OfferPresentationModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding =
            RvPosterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = offers[position]
        holder.bind(offer)
    }

    override fun getItemCount(): Int {
        return offers.size
    }

    fun submitList(newOffers: List<OfferPresentationModel>) {
        offers = newOffers
        notifyDataSetChanged()
    }


    class OfferViewHolder(private val binding: RvPosterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(offer: OfferPresentationModel) {
            with(binding) {
                Glide.with(posterRvPosterItem)
                    .load(getDrawableFromId(offer.id))
                    .apply(RequestOptions.bitmapTransform(multiRoundedCorner16))
                    .into(posterRvPosterItem)

                titleRvPosterItem.text = offer.title

                townRvPosterItem.text = offer.town

                priceRvPosterItem.text = APP_ACTIVITY.getString(
                    R.string.price_with_currency,
                    toCurrencyFormat(offer.price)
                )
            }
        }
    }
}