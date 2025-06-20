package com.yanschool.finapp.domain.splash

import kotlinx.coroutines.flow.StateFlow

interface AccountIdRepository {

    fun getCurrentAccountId(): StateFlow<Int?>

}