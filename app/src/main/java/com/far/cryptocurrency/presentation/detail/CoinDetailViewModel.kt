package com.far.cryptocurrency.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.far.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.far.cryptocurrency.common.Resource
import com.far.cryptocurrency.domain.useCase.getCoin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _getCoinState = mutableStateOf(CoinDetailState())
    val getCoinState: State<CoinDetailState> = _getCoinState

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoins(coinId)
        }
    }

    private fun getCoins(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getCoinState.value = CoinDetailState(coins = result.data)
                }
                is Resource.Error -> {
                    _getCoinState.value =
                        CoinDetailState(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _getCoinState.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}