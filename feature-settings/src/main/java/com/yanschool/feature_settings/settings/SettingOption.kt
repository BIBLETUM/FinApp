package com.yanschool.feature_settings.settings

import androidx.annotation.StringRes
import com.yanschool.ui.R

internal enum class SettingOption(@StringRes val titleRes: Int) {
    MAIN_COLOR(R.string.main_color),
    SOUNDS(R.string.sounds),
    HAPTICS(R.string.haptics),
    CODE_PASSWORD(R.string.code_password),
    SYNCHRONIZATION(R.string.synchronization),
    LANGUAGE(R.string.language),
    ABOUT_APP(R.string.about_app)
}
