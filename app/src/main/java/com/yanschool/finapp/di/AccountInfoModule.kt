package com.yanschool.finapp.di

import com.yanschool.finapp.data.account_info.AccountInfoRepositoryImpl
import com.yanschool.finapp.domain.account_info.AccountInfoRepository
import com.yanschool.finapp.domain.account_info.GetAccountInfoFlowUseCase
import com.yanschool.finapp.domain.account_info.IGetAccountInfoFlowUseCase
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