package com.example.storeapp.presentation.menu.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentAllTicketsBinding
import com.example.storeapp.presentation.menu.tickets.adapter.AllTicketsAdapter
import com.example.storeapp.presentation.menu.tickets.state.State
import com.example.storeapp.presentation.utilities.makeGone
import com.example.storeapp.presentation.utilities.makeVisible
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val FROM_CITY_KEY = "from_city_key"
private const val IN_CITY_KEY = "in_city_key"
private const val DATE_KEY = "date_key"

class AllTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAllTicketsBinding
    private val viewModel by viewModel<AllTicketsViewModel>()
    private val allTicketsAdapter = AllTicketsAdapter()
    private var fromCity = ""
    private var inCity = ""
    private var date = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTicketsBinding.inflate(layoutInflater)
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
                            binding.progressFrgAllTickets.makeGone()
                        }

                        is State.Loading -> {
                            binding.progressFrgAllTickets.makeVisible()
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewFrgAllTickets.adapter = allTicketsAdapter
        initData()
    }

    private fun initData() {
        viewModel.tickets.observe(viewLifecycleOwner) { allTickets ->
            allTicketsAdapter.submitList(allTickets)
        }
    }

    private fun getData() {
        fromCity = arguments?.getString(FROM_CITY_KEY) ?: ""
        inCity = arguments?.getString(IN_CITY_KEY) ?: ""
        date = arguments?.getString(DATE_KEY) ?: ""
    }

    private fun setupData() {
        binding.placeFrgAllTickets.text = "$fromCity-$inCity"

        binding.dateInfoFrgAllTickets.text = "$date , 1 пассажир"

        binding.btnBackFrgAllTickets.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(FROM_CITY_KEY, fromCity)
            bundle.putString(IN_CITY_KEY, inCity)
            findNavController().navigate(R.id.action_allTicketsFragment_to_flightsFragment, bundle)
        }
    }
}