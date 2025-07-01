package com.yanschool.data.common_repository

import com.yanschool.data.remote_data_source.api.AccountsService
import com.yanschool.data.remote_data_source.common_mappers.AccountShortMapper
import com.yanschool.domain.common_models.AccountInfo
import com.yanschool.domain.common_repository.CurrentAccountRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentAccountRepositoryImpl @Inject constructor(
    private val accountsService: AccountsService,
    private val accountShortMapper: AccountShortMapper,
) : CurrentAccountRepository {

    private val _currentAccountId = MutableStateFlow<AccountInfo?>(null)
    private val currentAccountId: StateFlow<AccountInfo?> = _currentAccountId.asStateFlow()

    override suspend fun loadAccount() {
        flowOf(accountsService.getAccounts().first())
            .map { accountShortMapper.mapDtoToDomain(it) }
            .retryWhen { cause, attempt ->
                if (cause is HttpException && cause.code() == INTERNAL_SERVER_ERROR && attempt < MAX_RETRIES) {
                    delay(RETRY_DELAY)
                    true
                } else {
                    false
                }
            }
            .collect {
                _currentAccountId.value = it
            }
    }

    override fun getCurrentAccountFlow(): StateFlow<AccountInfo?> = currentAccountId

    private companion object {
        const val MAX_RETRIES = 3
        const val RETRY_DELAY = 2000L
        const val INTERNAL_SERVER_ERROR = 500
    }
}
