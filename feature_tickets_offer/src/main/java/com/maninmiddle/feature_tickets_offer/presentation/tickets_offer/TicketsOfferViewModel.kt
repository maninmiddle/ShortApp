package com.maninmiddle.feature_tickets_offer.presentation.tickets_offer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maninmiddle.core.util.ApiState
import com.maninmiddle.feature_tickets_offer.domain.repository.TicketsOfferRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketsOfferViewModel(
    private val repository: TicketsOfferRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TicketsOfferUiState())
    val state: StateFlow<TicketsOfferUiState>
        get() = _state

    init {
        loadTicketsOffers()
    }

    private fun loadTicketsOffers() {
        _state.value = state.value.copy(
            ticketsOffer = null,
            isLoading = true,
            error = null
        )
        viewModelScope.launch {
            when (val result = repository.getTicketsOffer()) {
                is ApiState.Success -> {
                    _state.value = state.value.copy(
                        ticketsOffer = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is ApiState.Error -> {
                    _state.value = state.value.copy(
                        ticketsOffer = null,
                        isLoading = false,
                        error = result.message
                    )
                }

                else -> Log.e("Api Call Error", "${result.message}")
            }
        }
    }
}