package com.far.cryptocurrency.data.repository

import com.far.cryptocurrency.data.remote.service.CoinPaprikaApi
import com.far.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinPaprikaApi) : CoinRepository {

    override fun getCoins() = flow { emit(api.getCoins()) }

    override fun getCoinById(coinId: String) = flow { emit(api.getCoinById(coinId)) }
}