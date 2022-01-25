package com.far.cryptocurrency.domain.repository

import com.far.cryptocurrency.data.remote.dto.CoinDetailDto
import com.far.cryptocurrency.data.remote.dto.CoinDto
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<List<CoinDto>>
    fun getCoinById(coinId: String): Flow<CoinDetailDto>
}