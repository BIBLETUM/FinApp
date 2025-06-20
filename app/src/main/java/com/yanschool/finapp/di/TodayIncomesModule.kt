package com.yanschool.finapp.di

import com.yanschool.finapp.domain.today_incomes.GetTodayIncomesFlowUseCase
import com.yanschool.finapp.domain.today_incomes.IGetTodayIncomesFlowUseCase
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