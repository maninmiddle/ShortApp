package com.maninmiddle.feature_flights.presentation.flights

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.maninmiddle.core.util.FragmentProvider
import com.maninmiddle.core.util.MainActivityFragmentContract
import com.maninmiddle.feature_flights.R
import com.maninmiddle.feature_flights.databinding.BottomSheetLayoutBinding
import com.maninmiddle.feature_flights.databinding.FragmentFlightsBinding
import com.maninmiddle.feature_flights.presentation.adapters.OfferAdapter
import com.maninmiddle.feature_tickets_offer.presentation.tickets_offer.TicketsOfferFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class FlightsFragment : Fragment() {
    private var _binding: FragmentFlightsBinding? = null
    val viewModel by viewModel<FlightsViewModel>()
    private lateinit var dialog: BottomSheetDialog
    private val fragmentProvider: FragmentProvider by inject()

    private val binding: FragmentFlightsBinding
        get() = _binding ?: throw RuntimeException("FragmentFlightsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()

        binding.etToInput.setOnClickListener {
            if (binding.etWhereInput.text.isNotEmpty()) {
                showBottomSheet(binding.etWhereInput.text.toString())
            } else {
                binding.etWhereInput.error = getString(R.string.stringInputError)
            }
        }
    }

    private fun View.hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


    private fun startTicketsOfferFragment(where: String, to: String) {
        val mainActivity = requireActivity() as MainActivityFragmentContract
        val ticketsOfferFragment = TicketsOfferFragment.newInstance(where, to)
        mainActivity.addFragment(ticketsOfferFragment)
        dialog.hide()
    }

    private fun startStubFragment() {
        val mainActivity = requireActivity() as MainActivityFragmentContract
        mainActivity.addFragment(fragmentProvider.getStubFragment())
        dialog.hide()
    }

    private fun showBottomSheet(where: String) {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val dialogBinding = BottomSheetLayoutBinding.inflate(inflater)
        dialog.setContentView(dialogBinding.root)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialogBinding.etWhereInput.setText(where)
        dialog.window?.setWindowAnimations(R.style.SlideAnimation)
        dialog.show()


        dialogBinding.clIstanbul.setOnClickListener {
            binding.etToInput.hideKeyboard()
            startTicketsOfferFragment(
                where = dialogBinding.etWhereInput.text.toString(),
                dialogBinding.tvIstanbul.text.toString()
            )
        }
        dialogBinding.clPhuket.setOnClickListener {
            binding.etToInput.hideKeyboard()
            startTicketsOfferFragment(
                dialogBinding.etWhereInput.text.toString(),
                dialogBinding.tvPhuket.text.toString()
            )
        }
        dialogBinding.clSochi.setOnClickListener {
            binding.etToInput.hideKeyboard()
            startTicketsOfferFragment(
                where = dialogBinding.etWhereInput.text.toString(),
                dialogBinding.tvSochi.text.toString()
            )
        }

        dialogBinding.ivClose.setOnClickListener { binding.etToInput.text.clear() }

        dialogBinding.cvHardRoute.setOnClickListener {
            startStubFragment()
        }
        dialogBinding.cvAnywhere.setOnClickListener {
            dialogBinding.etToInput.setText(dialogBinding.tvSochi.text)
        }
        dialogBinding.cvWeekend.setOnClickListener {
            startStubFragment()
        }
        dialogBinding.cvHotTickets.setOnClickListener {
            startStubFragment()
        }


        dialogBinding.etToInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (dialogBinding.etToInput.text.isNotEmpty()) {
                    startTicketsOfferFragment(
                        dialogBinding.etWhereInput.text.toString(),
                        dialogBinding.etToInput.text.toString()
                    )
                    dialogBinding.etToInput.hideKeyboard()
                } else {
                    dialogBinding.etToInput.error = getString(R.string.stringInputError)
                }
                return@setOnEditorActionListener true
            }
            false
        }
    }


    private fun setupRv() {
        val offerAdapter = OfferAdapter(requireContext())
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    if (!state.isLoading) {
                        offerAdapter.submitList(state.offers)
                    }
                }
            }
        }
        binding.rvLayout.adapter = offerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}