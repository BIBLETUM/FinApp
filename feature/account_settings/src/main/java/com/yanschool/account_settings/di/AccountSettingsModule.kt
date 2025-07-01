package com.yanschool.account_settings.di

import com.yanschool.account_settings.data.AccountSettingsRepositoryImpl
import com.yanschool.account_settings.domain.AccountSettingsRepository
import com.yanschool.account_settings.domain.IUpdateAccountInfoUseCase
import com.yanschool.account_settings.domain.UpdateAccountInfoUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AccountSettingsModule {

    @Binds
    fun bindAccountSettingsRepository(
        impl: AccountSettingsRepositoryImpl
    ): AccountSettingsRepository

    @Binds
    fun bindUpdateAccountInfoUseCase(
        impl: UpdateAccountInfoUseCase
    ): IUpdateAccountInfoUseCase
}
