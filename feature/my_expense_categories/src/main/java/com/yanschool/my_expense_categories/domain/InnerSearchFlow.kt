package com.yanschool.my_expense_categories.domain

import com.yanschool.utils.constants.StringConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InnerSearchFlow @Inject constructor() {

    private val _searchFlow = MutableStateFlow<String>(StringConstants.emptyString)
    private val searchFlow = _searchFlow.asStateFlow()

    fun getSearchFlow(): StateFlow<String> = searchFlow

    fun setSearchQuery(value: String) {
        _searchFlow.value = value
    }

}