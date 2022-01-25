package com.far.cryptocurrency.presentation.list.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.far.cryptocurrency.common.Constants
import com.far.cryptocurrency.data.remote.dto.toCoin
import com.far.cryptocurrency.domain.usecase.getCoins.GetCoinsUseCase
import com.far.cryptocurrency.presentation.list.state.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _getCoinsState = mutableStateOf(CoinListState())
    val getCoinsState: State<CoinListState> = _getCoinsState

    init {
        getCoins()
    }

    private fun getCoins() {
        _getCoinsState.value = CoinListState(isLoading = true)
        getCoinsUseCase(
            onSuccess = {
                _getCoinsState.value = CoinListState(coins = it.map { coin -> coin.toCoin() })
            },
            onError = {
                _getCoinsState.value = CoinListState(error = it.message ?: Constants.DEFAULT_ERROR)
            }
        )
    }
}