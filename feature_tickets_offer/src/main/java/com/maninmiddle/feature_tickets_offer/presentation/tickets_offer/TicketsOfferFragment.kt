package com.maninmiddle.feature_tickets_offer.presentation.tickets_offer

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.maninmiddle.core.util.MainActivityFragmentContract
import com.maninmiddle.feature_tickets.presentation.tickets.TicketsFragment
import com.maninmiddle.feature_tickets_offer.R
import com.maninmiddle.feature_tickets_offer.databinding.FragmentTicketsOfferBinding
import com.maninmiddle.feature_tickets_offer.presentation.adapters.TicketsOfferAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TicketsOfferFragment : Fragment() {
    val viewModel by viewModel<TicketsOfferViewModel>()
    private var _binding: FragmentTicketsOfferBinding? = null

    private val binding: FragmentTicketsOfferBinding
        get() = _binding ?: throw RuntimeException("FragmentTicketsOfferBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsOfferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        getArgs()
        binding.btnShowAllTickets.setOnClickListener {
            startTicketsFragment()
        }
        binding.ivReverseTown.setOnClickListener {
            val whereText = binding.etWhereInput.text.toString()
            val toText = binding.etToInput.text.toString()

            if (whereText != toText) {
                binding.etWhereInput.setText(toText)
                binding.etToInput.setText(whereText)
            }
        }
        binding.btnBackDate.setOnClickListener {
            showDatePicker(true)
        }

        binding.secondBtn.setOnClickListener {
            showDatePicker(false)
        }

        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun showDatePicker(backDate: Boolean) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDateCalendar = Calendar.getInstance()
                selectedDateCalendar.set(selectedYear, selectedMonth, selectedDay)
                val selectedDate = formatDateToRussian(selectedDateCalendar)
                if (backDate)
                    binding.btnBackDate.text = selectedDate
                else
                    binding.secondBtn.text = selectedDate
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun formatDateToRussian(calendar: Calendar): String {
        val russianSymbols = DateFormatSymbols(Locale("ru")).apply {
            shortMonths = arrayOf(
                "янв", "фев", "мар", "апр", "май", "июн",
                "июл", "авг", "сен", "окт", "ноя", "дек"
            )
            shortWeekdays = arrayOf(
                "", "вс", "пн", "вт", "ср", "чт", "пт", "сб"
            )
        }
        val dateFormat = SimpleDateFormat("dd MMM, E", Locale("ru"))
        dateFormat.dateFormatSymbols = russianSymbols
        return dateFormat.format(calendar.time).replaceFirstChar { it.uppercase() }

    }

    private fun startTicketsFragment() {
        val ticketsFragment = TicketsFragment.newInstance(
            binding.etWhereInput.text.toString(),
            binding.etToInput.text.toString(),
            binding.secondBtn.text.toString()
        )
        val mainActivity = requireActivity() as MainActivityFragmentContract
        mainActivity.addFragment(ticketsFragment)
    }

    private fun getArgs() {
        val townWhere = arguments?.getString(TOWN_WHERE)
        val townTo = arguments?.getString(TOWN_TO)
        binding.etToInput.setText(townTo)
        binding.etWhereInput.setText(townWhere)
    }

    private fun setupRv() {
        val ticketsOfferAdapter = TicketsOfferAdapter()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    if (!state.isLoading) {
                        ticketsOfferAdapter.submitList(state.ticketsOffer?.take(3))
                    }
                }
            }
        }
        binding.rvLayoutFlights.adapter = ticketsOfferAdapter
        binding.rvLayoutFlights.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TOWN_WHERE = "town_where"
        private const val TOWN_TO = "town_to"

        fun newInstance(where: String, to: String): TicketsOfferFragment {
            return TicketsOfferFragment().apply {
                arguments = Bundle().apply {
                    putString(TOWN_WHERE, where)
                    putString(TOWN_TO, to)
                }
            }
        }
    }
}