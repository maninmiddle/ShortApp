package com.maninmiddle.shortapp.presentation.fragments.ticketsOffer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.maninmiddle.presentation.databinding.FragmentTicketsOfferBinding
import com.maninmiddle.shortapp.presentation.adapters.ticket_offer.TicketsOfferAdapter
import com.maninmiddle.shortapp.presentation.fragments.tickets.TicketsFragment
import com.maninmiddle.shortapp.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TicketsOfferFragment : Fragment() {
    private lateinit var viewModel: TicketsOfferViewModel
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
        viewModel = ViewModelProvider(this)[TicketsOfferViewModel::class.java]
        setupRv()
        getArgs()
        binding.btnShowAllTickets.setOnClickListener {
            startTicketsFragment()
        }
    }

    private fun startTicketsFragment() {
        val ticketsFragment = TicketsFragment.newInstance(
            binding.etWhereInput.text.toString(),
            binding.etToInput.text.toString()
        )
        val mainActivity = requireActivity() as MainActivity
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