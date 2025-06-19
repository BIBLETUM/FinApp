package com.yanschool.finapp.di

import com.yanschool.finapp.data.expenses.TransactionsRepositoryImpl
import com.yanschool.finapp.domain.expenses.GetTodayExpensesFlowUseCase
import com.yanschool.finapp.domain.expenses.IGetTodayExpensesFlowUseCase
import com.yanschool.finapp.domain.expenses.TodayTransactionsRepository
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

    @Binds
    fun bindTodayTransactionsRepository(
        impl: TransactionsRepositoryImpl
    ): TodayTransactionsRepository

}