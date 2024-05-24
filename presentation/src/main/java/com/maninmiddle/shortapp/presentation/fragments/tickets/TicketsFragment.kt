package com.maninmiddle.shortapp.presentation.fragments.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.maninmiddle.presentation.R
import com.maninmiddle.presentation.databinding.FragmentTicketsBinding
import com.maninmiddle.shortapp.presentation.adapters.tickets.TicketsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TicketsFragment : Fragment() {
    private lateinit var viewModel: TicketsViewModel
    private var _binding: FragmentTicketsBinding? = null
    private val binding: FragmentTicketsBinding
        get() = _binding ?: throw RuntimeException("FragmentTicketsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TicketsViewModel::class.java]
        val where = arguments?.getString(TOWN_WHERE)
        val to = arguments?.getString(TOWN_TO)

        binding.tvWhereTo.text = getString(R.string.stringWhereToType, where, to)

        binding.ivBackArrow.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        val ticketsAdapter = TicketsAdapter()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    if (!state.isLoading) {
                        ticketsAdapter.submitList(state.tickets)
                    }
                }
            }
        }

        binding.rvLayout.adapter = ticketsAdapter
        binding.rvLayout.layoutManager = LinearLayoutManager(context)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TOWN_WHERE = "town_where"
        private const val TOWN_TO = "town_to"

        fun newInstance(where: String, to: String): TicketsFragment {
            return TicketsFragment().apply {
                arguments = Bundle().apply {
                    putString(TOWN_WHERE, where)
                    putString(TOWN_TO, to)
                }
            }
        }
    }

}