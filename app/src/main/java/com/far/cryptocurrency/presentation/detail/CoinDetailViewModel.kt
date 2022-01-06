package com.far.cryptocurrency.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.far.cryptocurrency.common.Constants.DEFAULT_ERROR
import com.far.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.far.cryptocurrency.data.remote.dto.toCoinDetail
import com.far.cryptocurrency.domain.useCase.getCoin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _getCoinState = mutableStateOf(CoinDetailState())
    val getCoinState: State<CoinDetailState> = _getCoinState

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId -> getCoins(coinId) }
    }

    private fun getCoins(coinId: String) {
        viewModelScope.launch {
            _getCoinState.value = CoinDetailState(isLoading = true)
            getCoinUseCase(
                params = GetCoinUseCase.Params(coinId),
                onSuccess = { _getCoinState.value = CoinDetailState(coins = it.toCoinDetail()) },
                onError = {
                    _getCoinState.value = CoinDetailState(error = it.message ?: DEFAULT_ERROR)
                }
            )
        }
    }
}