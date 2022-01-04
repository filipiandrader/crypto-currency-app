package com.far.cryptocurrency.domain.repository

import com.far.cryptocurrency.data.remote.dto.CoinDetailDto
import com.far.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}