package com.yanschool.finapp.di

import com.yanschool.data.common_repository.CurrentAccountRepositoryImpl
import com.yanschool.domain.common_repository.CurrentAccountRepository
import com.yanschool.domain.common_usecase.GetCurrentCurrentAccountFlowUseCase
import com.yanschool.domain.common_usecase.IGetCurrentAccountFlowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AccountIdModule {

    @Singleton
    @Binds
    fun bindGetAccountIdFlowUseCase(
        impl: GetCurrentCurrentAccountFlowUseCase,
    ): IGetCurrentAccountFlowUseCase

    @Singleton
    @Binds
    fun bindAccountIdRepository(
        impl: CurrentAccountRepositoryImpl
    ): CurrentAccountRepository
}
