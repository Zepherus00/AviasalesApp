package com.example.storeapp.presentation.menu.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentFlightsBinding
import com.example.storeapp.presentation.menu.tickets.adapter.CarouselAdapter
import com.example.storeapp.presentation.menu.tickets.adapter.FlightsAdapter
import com.example.storeapp.presentation.menu.tickets.state.State
import com.example.storeapp.presentation.utilities.getSelectDateForAllTickets
import com.example.storeapp.presentation.utilities.getSelectDateForFlights
import com.example.storeapp.presentation.utilities.makeGone
import com.example.storeapp.presentation.utilities.makeVisible
import com.example.storeapp.presentation.utilities.toEditable
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

private const val FROM_CITY_KEY = "from_city_key"
private const val IN_CITY_KEY = "in_city_key"
private const val DATE_KEY = "date_key"

class FlightsFragment : Fragment() {
    private lateinit var binding: FragmentFlightsBinding
    private val viewModel by viewModel<FlightsViewModel>()
    private var fromCity = ""
    private var inCity = ""
    private var date = LocalDate.now()
    private lateinit var dialog: BottomSheetDialog
    private val flightsAdapter = FlightsAdapter()
    private var adapterCarousel = CarouselAdapter(
        onItemClicked = { position -> onItemCarouselClicked(position) }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getData()
        setupData()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        State.Success -> {
                            binding.progressFrgFlights.makeGone()
                        }

                        is State.Loading -> {
                            binding.progressFrgFlights.makeVisible()
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewFrgFlights.adapter = flightsAdapter
        binding.recyclerViewCarouselFrgFlights.adapter = adapterCarousel
        initData()
    }

    private fun initData() {
        viewModel.ticketsOffers.observe(viewLifecycleOwner) { ticketsOffers ->
            flightsAdapter.submitList(ticketsOffers)
        }
    }

    private fun getData() {
        fromCity = arguments?.getString(FROM_CITY_KEY) ?: ""
        inCity = arguments?.getString(IN_CITY_KEY) ?: ""
    }

    private fun setupData() {
        with(binding) {
            fromCityFrgFlights.text = fromCity.toEditable()
            inCityFrgFlights.text = inCity.toEditable()

            icBackFrgFlights.setOnClickListener {
                findNavController().navigate(R.id.action_flightsFragment_to_ticketsFragment)
            }

            icSortFrgFlights.setOnClickListener {
                val tempInCity = inCity
                inCity = fromCity
                fromCity = tempInCity
                fromCityFrgFlights.text = fromCity.toEditable()
                inCityFrgFlights.text = inCity.toEditable()
            }

            btnAllTickets.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(FROM_CITY_KEY, fromCity)
                bundle.putString(IN_CITY_KEY, inCity)
                bundle.putString(DATE_KEY, getSelectDateForAllTickets(date))
                findNavController().navigate(
                    R.id.action_flightsFragment_to_allTicketsFragment,
                    bundle
                )
            }
        }
    }

    private fun onItemCarouselClicked(position: Int) {
        if (position == 1) {
            showDateDialog()
        }
    }

    private fun showDateDialog() {
        dialog = BottomSheetDialog(requireContext()).apply {
            this.setContentView(R.layout.fragment_date_window)
            val datePicker = findViewById<DatePicker>(R.id.date_picker_view)
            val btnApply = findViewById<AppCompatButton>(R.id.btn_apply)

            btnApply?.setOnClickListener {
                val year = datePicker?.year ?: 0
                val month = datePicker?.month ?: 0
                val dayOfMonth = datePicker?.dayOfMonth ?: 0

                val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)

                date = selectedDate
                adapterCarousel.setupDate(getSelectDateForFlights(date))

                dismiss()
            }
        }

        dialog.show()
    }
}