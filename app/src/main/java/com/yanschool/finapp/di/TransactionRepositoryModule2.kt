package com.yanschool.finapp.di

import com.yanschool.finapp.data.transactions.TransactionsRepositoryImpl
import com.yanschool.finapp.domain.TransactionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TransactionRepositoryModule2 {

    @Binds
    fun bindTodayTransactionsRepository(
        impl: TransactionsRepositoryImpl
    ): TransactionsRepository

}