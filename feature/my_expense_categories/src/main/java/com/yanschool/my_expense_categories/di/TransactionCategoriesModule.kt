package com.yanschool.my_expense_categories.di

import com.yanschool.my_expense_categories.data.TransactionCategoriesRepositoryImpl
import com.yanschool.my_expense_categories.domain.GetTransactionCategoriesFlowUseCase
import com.yanschool.my_expense_categories.domain.IGetTransactionCategoriesFlowUseCase
import com.yanschool.my_expense_categories.domain.TransactionCategoriesRepository
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
