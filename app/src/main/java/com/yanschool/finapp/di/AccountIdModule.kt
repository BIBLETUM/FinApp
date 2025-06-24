package com.yanschool.finapp.di

import com.yanschool.data.common_repository.AccountIdRepositoryImpl
import com.yanschool.domain.common_repository.AccountIdRepository
import com.yanschool.domain.common_usecase.GetAccountIdFlowUseCase
import com.yanschool.domain.common_usecase.IGetAccountIdFlowUseCase
import com.yanschool.splash.domain.GetIsReadyToProceedFromSplashScreenFlowInteractor
import com.yanschool.splash.domain.IGetIsReadyToProceedFromSplashScreenFlowInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AccountIdModule {

    @Binds
    fun bindGetIsReadyToProceedFromSplashScreenFlow(
        impl: GetIsReadyToProceedFromSplashScreenFlowInteractor,
    ): IGetIsReadyToProceedFromSplashScreenFlowInteractor

    @Binds
    fun bindGetAccountIdFlowUseCase(
        impl: GetAccountIdFlowUseCase,
    ): IGetAccountIdFlowUseCase

    @Binds
    fun bindAccountIdRepository(
        impl: AccountIdRepositoryImpl
    ): AccountIdRepository

}