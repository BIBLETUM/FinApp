package com.yanschool.account_info.di

import com.yanschool.account_info.data.AccountInfoRepositoryImpl
import com.yanschool.account_info.domain.AccountInfoRepository
import com.yanschool.account_info.domain.GetAccountInfoFlowUseCase
import com.yanschool.account_info.domain.IGetAccountInfoFlowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AccountInfoModule {

    @Binds
    fun bindGetAccountInfoFlowUseCase(
        impl: GetAccountInfoFlowUseCase,
    ): IGetAccountInfoFlowUseCase

    @Binds
    fun bindAccountInfoRepository(
        impl: AccountInfoRepositoryImpl
    ): AccountInfoRepository
}
