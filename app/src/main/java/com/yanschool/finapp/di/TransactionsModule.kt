package com.yanschool.finapp.di

import com.yanschool.data.common_repository.TransactionsRepositoryImpl
import com.yanschool.domain.common_repository.TransactionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TransactionsModule {

    @Binds
    fun bindTransactionsRepository(
        impl: TransactionsRepositoryImpl
    ): TransactionsRepository

}