package com.yanschool.today_incomes.di

import com.yanschool.today_incomes.domain.GetTodayIncomesFlowUseCase
import com.yanschool.today_incomes.domain.IGetTodayIncomesFlowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TodayIncomesModule {

    @Binds
    fun bindGetTodayIncomesFlowUseCase(
        impl: GetTodayIncomesFlowUseCase,
    ): IGetTodayIncomesFlowUseCase

}