import java.io.FileInputStream
import java.util.Properties

plugins {
    id("android-app-module")
    id("check-conventions-plugin")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

val localProperties = Properties()
localProperties.load(FileInputStream(rootProject.file("local.properties")))

android {
    defaultConfig {
        applicationId = Const.NAMESPACE
        versionCode = 1
        versionName = "1.0"
        targetSdk = Const.COMPILE_SKD

        buildConfigField("String", "apiKey", "\"${localProperties["apiKey"]}\"")
    }
}

dependencies {
    api(project(":core:ui"))
    implementation(project(":core:utils"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    implementation(project(":feature:settings"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:account_info"))
    implementation(project(":feature:my_expense_categories"))
    implementation(project(":feature:account_settings"))
    implementation(project(":feature:today_expenses"))
    implementation(project(":feature:today_incomes"))
    implementation(project(":feature:history_incomes"))
    implementation(project(":feature:history_expenses"))

    ksp(libs.hilt.android.compiler)

    implementation(libs.retrofit)

    implementation(libs.lottie)
}
