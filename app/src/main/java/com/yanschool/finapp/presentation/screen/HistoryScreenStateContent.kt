package com.yanschool.finapp.presentation.screen

import com.yanschool.finapp.presentation.model.TransactionDetailUi

interface HistoryScreenStateContent {
    val startDate: String
    val endDate: String
    val total: String
    val transactions: List<TransactionDetailUi>
}