package com.yanschool.finapp.di

import com.yanschool.data.common_repository.AccountIdRepositoryImpl
import com.yanschool.domain.common_repository.AccountIdRepository
import com.yanschool.splash.data.GetIsReadyToProceedFromSplashScreenFlowUseCase
import com.yanschool.splash.data.IGetIsReadyToProceedFromSplashScreenFlowUseCase
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