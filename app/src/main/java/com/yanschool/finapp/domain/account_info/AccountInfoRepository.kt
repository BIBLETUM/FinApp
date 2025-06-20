package com.yanschool.finapp.domain.account_info

import kotlinx.coroutines.flow.Flow

interface AccountInfoRepository {

    fun getAccountInfo(): Flow<Result<AccountInfo>>

}