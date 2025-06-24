package com.yanschool.domain.common_repository

import kotlinx.coroutines.flow.StateFlow

interface AccountIdRepository {

    suspend fun loadAccountId()

    fun getCurrentAccountIdFlow(): StateFlow<Int?>

}