package com.yanschool.finapp.di

import com.yanschool.finapp.data.AccountIdRepositoryImpl
import com.yanschool.finapp.domain.splash.AccountIdRepository
import com.yanschool.finapp.domain.splash.GetIsReadyToProceedFromSplashScreenFlowUseCase
import com.yanschool.finapp.domain.splash.IGetIsReadyToProceedFromSplashScreenFlowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AccountIdModule {

    @Binds
    fun bindGetIsReadyToProceedFromSplashScreenFlow(
        impl: GetIsReadyToProceedFromSplashScreenFlowUseCase,
    ): IGetIsReadyToProceedFromSplashScreenFlowUseCase

    @Binds
    fun bindAccountIdRepository(
        impl: AccountIdRepositoryImpl
    ): AccountIdRepository

}