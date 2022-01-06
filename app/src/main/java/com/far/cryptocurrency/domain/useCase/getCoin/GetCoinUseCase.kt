package com.far.cryptocurrency.domain.useCase.getCoin

import com.far.cryptocurrency.common.UseCase
import com.far.cryptocurrency.data.remote.dto.CoinDetailDto
import com.far.cryptocurrency.domain.exception.EmptyParamException
import com.far.cryptocurrency.domain.exception.MissingParamsException
import com.far.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) : UseCase<CoinDetailDto, GetCoinUseCase.Params>() {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.coinId.isEmpty() -> throw EmptyParamException()
        else -> repository.getCoinById(params.coinId)
    }

    data class Params(var coinId: String)
}