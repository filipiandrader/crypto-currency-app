package com.far.cryptocurrency.data.repository

import com.far.cryptocurrency.data.remote.CoinPaprikaApi
import com.far.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinPaprikaApi) : CoinRepository {

    override suspend fun getCoins() = api.getCoins()

    override suspend fun getCoinById(coinId: String) = api.getCoinById(coinId)
}