package com.yanschool.finapp.di

import com.yanschool.finapp.data.transaction_categories.TransactionCategoriesRepositoryImpl
import com.yanschool.finapp.domain.transaction_categories.GetTransactionCategoriesFlowUseCase
import com.yanschool.finapp.domain.transaction_categories.IGetTransactionCategoriesFlowUseCase
import com.yanschool.finapp.domain.transaction_categories.TransactionCategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TransactionCategoriesModule {

    @Binds
    fun bindGetCategoriesFlowUseCase(
        impl: GetTransactionCategoriesFlowUseCase,
    ): IGetTransactionCategoriesFlowUseCase

    @Binds
    fun bindTransactionCategoriesRepository(
        impl: TransactionCategoriesRepositoryImpl
    ): TransactionCategoriesRepository
}