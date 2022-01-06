package com.far.cryptocurrency.di

import com.far.cryptocurrency.common.Constants
import com.far.cryptocurrency.data.remote.service.CoinPaprikaApi
import com.far.cryptocurrency.data.repository.CoinRepositoryImpl
import com.far.cryptocurrency.domain.repository.CoinRepository
import com.far.cryptocurrency.domain.usecase.getCoin.GetCoinUseCase
import com.far.cryptocurrency.domain.usecase.getCoins.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetCoinUseCase(repository: CoinRepository) = GetCoinUseCase(repository)

    @Provides
    @Singleton
    fun provideGetCoinsUseCase(repository: CoinRepository) = GetCoinsUseCase(repository)
}