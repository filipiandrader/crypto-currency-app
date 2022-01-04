package com.far.cryptocurrency.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.far.cryptocurrency.common.Resource
import com.far.cryptocurrency.domain.useCase.getCoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase) :
    ViewModel() {

    private val _getCoinsState = mutableStateOf(CoinListState())
    val getCoinsState: State<CoinListState> = _getCoinsState

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getCoinsState.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _getCoinsState.value =
                        CoinListState(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _getCoinsState.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}