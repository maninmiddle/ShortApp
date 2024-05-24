package com.maninmiddle.shortapp.presentation.fragments.flights

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.maninmiddle.presentation.R
import com.maninmiddle.presentation.databinding.BottomSheetLayoutBinding
import com.maninmiddle.presentation.databinding.FragmentFlightsBinding
import com.maninmiddle.shortapp.presentation.adapters.offer.OfferAdapter
import com.maninmiddle.shortapp.presentation.fragments.stub.StubFragment
import com.maninmiddle.shortapp.presentation.fragments.ticketsOffer.TicketsOfferFragment
import com.maninmiddle.shortapp.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FlightsFragment : Fragment() {
    private lateinit var viewModel: FlightsViewModel
    private var _binding: FragmentFlightsBinding? = null
    private lateinit var dialog: BottomSheetDialog
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
        viewModel = ViewModelProvider(this)[FlightsViewModel::class.java]
        setupRv()

        binding.etToInput.setOnClickListener {
            if (binding.etWhereInput.text.isNotEmpty()) {
                showBottomSheet(binding.etWhereInput.text.toString())
                Log.d("Test", "SEND!")
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
        val mainActivity = requireActivity() as MainActivity
        val ticketsOfferFragment = TicketsOfferFragment.newInstance(where, to)
        mainActivity.addFragment(ticketsOfferFragment)
        dialog.hide()
    }

    private fun startStubFragment() {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.addFragment(StubFragment())
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