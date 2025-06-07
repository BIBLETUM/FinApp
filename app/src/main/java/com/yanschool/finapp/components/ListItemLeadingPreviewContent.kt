package com.yanschool.finapp.components

sealed interface ListItemLeadingPreviewContent {
    data class Literals(val value: String) : ListItemLeadingPreviewContent
    data class Emoji(val value: String) : ListItemLeadingPreviewContent
}