package com.yanschool.history_incomes.di

import com.yanschool.history_incomes.domain.ITransactionsIncomeHistoryInteractor
import com.yanschool.history_incomes.domain.TransactionsIncomeHistoryInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HistoryIncomeModule {

    @Binds
    fun bindTransactionsIncomeHistoryInteractor(
        impl: TransactionsIncomeHistoryInteractor,
    ): ITransactionsIncomeHistoryInteractor
}
