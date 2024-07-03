package com.maninmiddle.feature_flights.presentation.flights

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maninmiddle.core.util.ApiState
import com.maninmiddle.feature_flights.domain.repository.FlightsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlightsViewModel(
    private val repository: FlightsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(FlightsUiState())

    val state: StateFlow<FlightsUiState>
        get() = _state

    init {
        loadOffers()
    }

    private fun loadOffers() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true,
                error = null,
                offers = null
            )
            when (val result = repository.getOffers()) {
                is ApiState.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = null,
                        offers = result.data
                    )
                }

                is ApiState.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message,
                        offers = null
                    )
                }

                else -> Log.e("Api Call Error!", "${result.message}")
            }
        }
    }
}