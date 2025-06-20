package com.yanschool.finapp.di

import com.yanschool.finapp.domain.history_income.ITransactionsIncomeHistoryInteractor
import com.yanschool.finapp.domain.history_income.TransactionsIncomeHistoryInteractor
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