package com.yanschool.today_expenses.di

import com.yanschool.today_expenses.domain.GetTodayExpensesFlowUseCase
import com.yanschool.today_expenses.domain.IGetTodayExpensesFlowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TodayExpensesModule {

    @Binds
    fun bindGetTodayExpensesFlowUseCase(
        impl: GetTodayExpensesFlowUseCase,
    ): IGetTodayExpensesFlowUseCase
}
