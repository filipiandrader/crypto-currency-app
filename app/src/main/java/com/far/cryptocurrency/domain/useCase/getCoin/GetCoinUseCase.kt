package com.far.cryptocurrency.domain.useCase.getCoin

import com.far.cryptocurrency.common.Resource
import com.far.cryptocurrency.data.remote.dto.toCoinDetail
import com.far.cryptocurrency.domain.model.CoinDetail
import com.far.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoinById(coinId).toCoinDetail()
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