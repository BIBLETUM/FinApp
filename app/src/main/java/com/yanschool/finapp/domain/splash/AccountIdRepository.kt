package com.yanschool.finapp.domain.splash

import kotlinx.coroutines.flow.StateFlow

interface AccountIdRepository {

    suspend fun loadAccountId()

    fun getCurrentAccountId(): StateFlow<Int?>

}