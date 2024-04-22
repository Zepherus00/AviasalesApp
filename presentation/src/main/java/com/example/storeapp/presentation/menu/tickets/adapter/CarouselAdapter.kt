package com.example.storeapp.presentation.menu.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.databinding.RvCarouselItemBinding
import com.example.storeapp.presentation.utilities.getCurrentDate
import com.example.storeapp.presentation.utilities.makeGone
import com.google.android.material.textview.MaterialTextView

class CarouselAdapter(
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<CarouselViewHolder>() {

    private var itemsList = mutableListOf("Обратно", "", "1,эконом", "Фильтры")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(
            RvCarouselItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        setupData(holder, position)
        setupListeners(holder, position)
    }

    private fun setupData(holder: CarouselViewHolder, position: Int) {
        when (val item = itemsList[position]) {
            "Обратно" -> {
                holder.textItem.text = item
                holder.icon.setImageResource(R.drawable.ic_add)
            }

            "" -> {
                holder.textItem.text = getCurrentDate()
                holder.icon.makeGone()
            }

            "1,эконом" -> {
                holder.textItem.text = item
                holder.icon.setImageResource(R.drawable.ic_profile)
            }

            else -> {
                holder.textItem.text = item
                holder.icon.setImageResource(R.drawable.ic_filter_white)
            }
        }
    }

    private fun setupListeners(holder: CarouselViewHolder, position: Int) {
        holder.item.setOnClickListener {
            onItemClicked(position)
        }
    }

    fun setupDate(date: String) {
        itemsList[1] = date
        notifyDataSetChanged()
    }
}

class CarouselViewHolder(binding: RvCarouselItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val item: LinearLayoutCompat = binding.layoutRvCarouselItem
    val textItem: MaterialTextView = binding.textRvCarouselItem
    val icon: AppCompatImageView = binding.icRvCarouselItem
}

