package com.far.cryptocurrency.domain.useCase.getCoins

import com.far.cryptocurrency.common.Resource
import com.far.cryptocurrency.data.remote.dto.toCoin
import com.far.cryptocurrency.domain.model.Coin
import com.far.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "An unexpected error occured."))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.message ?: "Couldn't reach server. Chever your internet connection."
                )
            )
        }
    }
}