package com.yanschool.splash.di

import com.yanschool.splash.domain.GetIsReadyToProceedFromSplashScreenFlowInteractor
import com.yanschool.splash.domain.IGetIsReadyToProceedFromSplashScreenFlowInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SplashModule {

    @Binds
    fun bindGetIsReadyToProceedFromSplashScreenFlow(
        impl: GetIsReadyToProceedFromSplashScreenFlowInteractor,
    ): IGetIsReadyToProceedFromSplashScreenFlowInteractor
}
