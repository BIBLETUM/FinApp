package com.yanschool.finapp.data

import com.yanschool.finapp.domain.splash.AccountIdRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountIdRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : AccountIdRepository {

    private val _currentAccountId = MutableStateFlow<Int?>(null)
    private val currentAccountId: StateFlow<Int?> = _currentAccountId.asStateFlow()

    override suspend fun loadAccountId() {
        flow {
            emit(apiService.getAccounts().first().id)
        }
            .retryWhen { cause, attempt ->
                if (cause is HttpException && cause.code() == 500 && attempt < 3) {
                    delay(2000)
                    true
                } else {
                    false
                }
            }
            .collect {
                _currentAccountId.value = it
            }
    }


    override fun getCurrentAccountId(): StateFlow<Int?> = currentAccountId

}