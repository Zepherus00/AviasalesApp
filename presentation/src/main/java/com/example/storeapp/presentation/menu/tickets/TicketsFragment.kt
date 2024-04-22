package com.example.storeapp.presentation.menu.tickets

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentTicketsBinding
import com.example.storeapp.presentation.menu.tickets.adapter.OfferAdapter
import com.example.storeapp.presentation.menu.tickets.state.State
import com.example.storeapp.presentation.utilities.AppOnTextChangedWatcher
import com.example.storeapp.presentation.utilities.makeGone
import com.example.storeapp.presentation.utilities.makeVisible
import com.example.storeapp.presentation.utilities.multiRoundedCorner24
import com.example.storeapp.presentation.utilities.toEditable
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.regex.Pattern

private const val LAST_INPUT_KEY = "last_input_key"
private const val FROM_CITY_KEY = "from_city_key"
private const val IN_CITY_KEY = "in_city_key"

class TicketsFragment : Fragment() {
    private lateinit var binding: FragmentTicketsBinding
    private val viewModel by viewModel<TicketsViewModel>()
    private val offerAdapter = OfferAdapter()
    private lateinit var dialog: BottomSheetDialog
    private val cyrillicPattern: Pattern = Pattern.compile("[А-Яа-я]*")
    private var fromCity = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupData()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        State.Success -> {
                            binding.progressFrgTickets.makeGone()
                        }

                        is State.Loading -> {
                            binding.progressFrgTickets.makeVisible()
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewFrgTickets.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewFrgTickets.adapter = offerAdapter
        initData()
    }

    private fun initData() {
        viewModel.offers.observe(viewLifecycleOwner) { offers ->
            offerAdapter.submitList(offers)
        }
    }

    private fun setupData() {
        with(binding) {
            setupCyrillicFilter()

            val sharedPreferences: SharedPreferences =
                requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
            val lastInput = sharedPreferences.getString(LAST_INPUT_KEY, "")
            fromFrgTickets.setText(lastInput)
            fromCity = lastInput ?: ""

            fromFrgTickets.addTextChangedListener(AppOnTextChangedWatcher { from ->
                with(sharedPreferences.edit()) {
                    fromCity = from.toString()
                    putString(LAST_INPUT_KEY, from.toString())
                    apply()
                }
            })

            inFrgTickets.setOnClickListener {
                showSelectStudentDg()
            }
        }
    }

    private fun setupCyrillicFilter() {
        binding.fromFrgTickets.filters = arrayOf(cyrillicFilter)
        binding.inFrgTickets.filters = arrayOf(cyrillicFilter)
    }

    private fun showSelectStudentDg() {
        dialog = BottomSheetDialog(requireContext()).apply {
            this.setContentView(R.layout.fragment_modal_window)
            val stambul = findViewById<ConstraintLayout>(R.id.stambul_frg_modal_window)
            val sochi = findViewById<ConstraintLayout>(R.id.sochi_frg_modal_window)
            val phuket = findViewById<ConstraintLayout>(R.id.phuket_frg_modal_window)

            val imgStambul = findViewById<AppCompatImageView>(R.id.img_stambul)
            val imgSochi = findViewById<AppCompatImageView>(R.id.img_sochi)
            val imgPhuket = findViewById<AppCompatImageView>(R.id.img_phuket)

            val fromCityText = findViewById<TextInputEditText>(R.id.from_frg_modal_window)
            val btnEverywhere = findViewById<LinearLayoutCompat>(R.id.btn_everywhere)

            fromCityText?.text = fromCity.toEditable()

            Glide.with(imgStambul as View)
                .load(R.drawable.img_stambul)
                .apply(RequestOptions.bitmapTransform(multiRoundedCorner24))
                .into(imgStambul)
            Glide.with(imgSochi as View)
                .load(R.drawable.img_sochi)
                .apply(RequestOptions.bitmapTransform(multiRoundedCorner24))
                .into(imgSochi)
            Glide.with(imgPhuket as View)
                .load(R.drawable.img_phuket)
                .apply(RequestOptions.bitmapTransform(multiRoundedCorner24))
                .into(imgPhuket)

            btnEverywhere?.setOnClickListener {
                fromCityText?.text = "Куда угодно".toEditable()
                dialog.dismiss()
                val bundle = Bundle()
                bundle.putString(FROM_CITY_KEY, fromCity)
                bundle.putString(IN_CITY_KEY, "Куда угодно")
                findNavController().navigate(R.id.action_ticketsFragment_to_flightsFragment, bundle)
            }

            stambul?.setOnClickListener {
                fromCityText?.text = "Стамбул".toEditable()
                dialog.dismiss()
                val bundle = Bundle()
                bundle.putString(FROM_CITY_KEY, fromCity)
                bundle.putString(IN_CITY_KEY, "Стамбул")
                findNavController().navigate(R.id.action_ticketsFragment_to_flightsFragment, bundle)
            }

            sochi?.setOnClickListener {
                fromCityText?.text = "Сочи".toEditable()
                dialog.dismiss()
                val bundle = Bundle()
                bundle.putString(FROM_CITY_KEY, fromCity)
                bundle.putString(IN_CITY_KEY, "Сочи")
                findNavController().navigate(R.id.action_ticketsFragment_to_flightsFragment, bundle)
            }

            phuket?.setOnClickListener {
                fromCityText?.text = "Пхукет".toEditable()
                dialog.dismiss()
                val bundle = Bundle()
                bundle.putString(FROM_CITY_KEY, fromCity)
                bundle.putString(IN_CITY_KEY, "Пхукет")
                findNavController().navigate(R.id.action_ticketsFragment_to_flightsFragment, bundle)
            }
        }

        dialog.show()
    }

    private val cyrillicFilter = InputFilter { source, start, end, dest, dstart, dend ->
        val filtered = StringBuilder()
        for (i in start until end) {
            val char = source[i]
            if (cyrillicPattern.matcher(char.toString()).matches()) {
                filtered.append(char)
            }
        }
        filtered.toString()
    }
}