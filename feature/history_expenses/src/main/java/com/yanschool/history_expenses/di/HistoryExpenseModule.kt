package com.yanschool.history_expenses.di

import com.yanschool.history_expenses.domain.ITransactionsExpenseHistoryInteractor
import com.yanschool.history_expenses.domain.TransactionsExpenseHistoryInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface HistoryExpenseModule {

    @Binds
    fun bindTransactionsExpenseHistoryInteractor(
        impl: TransactionsExpenseHistoryInteractor,
    ): ITransactionsExpenseHistoryInteractor

}