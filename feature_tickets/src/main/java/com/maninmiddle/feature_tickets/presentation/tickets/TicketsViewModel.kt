package com.maninmiddle.feature_tickets.presentation.tickets

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maninmiddle.core.util.ApiState
import com.maninmiddle.feature_tickets.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketsViewModel(
    private val repository: TicketsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TicketsUiState())
    val state: StateFlow<TicketsUiState>
        get() = _state

    init {
        getTickets()
    }

    private fun getTickets() {
        _state.value = state.value.copy(
            tickets = null,
            isLoading = true,
            error = null
        )
        viewModelScope.launch {
            when (val result = repository.getTickets()) {
                is ApiState.Success -> {
                    _state.value = state.value.copy(
                        tickets = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is ApiState.Error -> {
                    _state.value = state.value.copy(
                        tickets = null,
                        isLoading = false,
                        error = null
                    )
                }

                else -> Log.e("Api call error", "${result.message}")
            }
        }
    }
}