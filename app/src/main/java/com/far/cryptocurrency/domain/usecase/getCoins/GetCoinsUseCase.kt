package com.far.cryptocurrency.domain.usecase.getCoins

import com.far.cryptocurrency.common.UseCase
import com.far.cryptocurrency.data.remote.dto.CoinDto
import com.far.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) :
    UseCase<List<CoinDto>, Unit>() {

    override fun run(params: Unit?) = repository.getCoins()
}