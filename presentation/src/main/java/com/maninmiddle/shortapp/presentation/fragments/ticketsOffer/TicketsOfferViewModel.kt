package com.maninmiddle.shortapp.presentation.fragments.ticketsOffer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maninmiddle.shortapp.domain.repository.ShortRepository
import com.maninmiddle.shortapp.domain.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketsOfferViewModel @Inject constructor(
    private val repository: ShortRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TicketsOfferUiState())
    val state: StateFlow<TicketsOfferUiState>
        get() = _state

    init {
        getTicketsOffer()
    }

    private fun getTicketsOffer() {
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
                        error = result.message,
                        isLoading = false
                    )
                }

                else -> Log.e("Api call error", "${result.message}")
            }
        }
    }
}