package com.yanschool.finapp.di

import com.yanschool.data.common_repository.CurrentAccountRepositoryImpl
import com.yanschool.domain.common_repository.CurrentAccountRepository
import com.yanschool.domain.common_usecase.GetCurrentCurrentAccountFlowUseCase
import com.yanschool.domain.common_usecase.IGetCurrentAccountFlowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AccountIdModule {

    @Binds
    fun bindGetAccountIdFlowUseCase(
        impl: GetCurrentCurrentAccountFlowUseCase,
    ): IGetCurrentAccountFlowUseCase

    @Binds
    fun bindAccountIdRepository(
        impl: CurrentAccountRepositoryImpl
    ): CurrentAccountRepository
}
