package com.yanschool.finapp.di

import com.yanschool.finapp.domain.history_expense.ITransactionsExpenseHistoryInteractor
import com.yanschool.finapp.domain.history_expense.TransactionsExpenseHistoryInteractor
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