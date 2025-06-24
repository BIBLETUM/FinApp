package com.yanschool.account_info.domain

import kotlinx.coroutines.flow.Flow

interface AccountInfoRepository {

    fun getAccountInfo(accountId: Int): Flow<Result<AccountInfo>>

}